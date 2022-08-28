/*
 * (c) Copyright 2022 Birch Solutions Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fern.java.client.generators;

import com.fern.ir.model.errors.DeclaredErrorName;
import com.fern.ir.model.services.http.HttpEndpoint;
import com.fern.ir.model.services.http.HttpService;
import com.fern.java.client.ClientGeneratorContext;
import com.fern.java.client.GeneratedEndpointRequestOutput;
import com.fern.java.generators.AbstractFileGenerator;
import com.fern.java.generators.object.EnrichedObjectProperty;
import com.fern.java.generators.object.ObjectTypeSpecGenerator;
import com.fern.java.output.AbstractGeneratedFileOutput;
import com.fern.java.output.GeneratedAuthFilesOutput;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.StringUtils;

public final class HttpEndpointFileGenerator extends AbstractFileGenerator {

    private static final String REQUEST_CLASS_NAME = "Request";
    private static final String AUTH_REQUEST_PARAMETER = "authOverride";
    private final HttpEndpoint httpEndpoint;
    private final List<ParameterSpec> serviceInterfaceMethodParameters;

    private final Optional<GeneratedAuthFilesOutput> maybeAuth;
    private final Map<DeclaredErrorName, AbstractGeneratedFileOutput> generatedErrors;

    public HttpEndpointFileGenerator(
            ClientGeneratorContext generatorContext,
            HttpService httpService,
            HttpEndpoint httpEndpoint,
            List<ParameterSpec> serviceInterfaceMethodParameters,
            Optional<GeneratedAuthFilesOutput> maybeAuth,
            Map<DeclaredErrorName, AbstractGeneratedFileOutput> generatedErrors) {
        super(
                generatorContext.getPoetClassNameFactory().getEndpointClassName(httpService.getName(), httpEndpoint),
                generatorContext);
        this.httpEndpoint = httpEndpoint;
        this.serviceInterfaceMethodParameters = serviceInterfaceMethodParameters;
        this.maybeAuth = maybeAuth;
        this.generatedErrors = generatedErrors;
    }

    @Override
    public GeneratedEndpointRequestOutput generateFile() {
        GeneratedEndpointRequestOutput.Builder outputBuilder = GeneratedEndpointRequestOutput.builder();
        TypeSpec.Builder endpointTypeSpecBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .build());
        endpointTypeSpecBuilder.addType(generateNestedRequestType(outputBuilder));
        TypeSpec endpointTypeSpec = endpointTypeSpecBuilder.build();
        JavaFile endpointJavaFile =
                JavaFile.builder(className.packageName(), endpointTypeSpec).build();
        return outputBuilder.className(className).javaFile(endpointJavaFile).build();
    }

    private TypeSpec generateNestedRequestType(GeneratedEndpointRequestOutput.Builder outputBuilder) {
        ClassName requestClassName = className.nestedClass(REQUEST_CLASS_NAME);
        List<EnrichedObjectProperty> enrichedObjectProperties = getRequestParameters().stream()
                .map(parameterSpec -> EnrichedObjectProperty.builder()
                        .camelCaseKey(parameterSpec.name)
                        .pascalCaseKey(StringUtils.capitalize(parameterSpec.name))
                        .poetTypeName(parameterSpec.type)
                        .fromInterface(false)
                        .build())
                .collect(Collectors.toList());
        ObjectTypeSpecGenerator objectTypeSpecGenerator =
                new ObjectTypeSpecGenerator(requestClassName, enrichedObjectProperties, Collections.emptyList(), false);
        TypeSpec requestTypeSpec = objectTypeSpecGenerator.generate();
        if (httpEndpoint.getAuth() && maybeAuth.isPresent()) {
            outputBuilder.authMethodSpec(enrichedObjectProperties.get(0).getterProperty());
        }
        outputBuilder.requestClassName(requestClassName);
        outputBuilder.addAllEnrichedObjectProperties(enrichedObjectProperties);
        outputBuilder.requestTypeSpec(requestTypeSpec);

        return requestTypeSpec;
    }

    private List<ParameterSpec> getRequestParameters() {
        List<ParameterSpec> requestParameters = new ArrayList<>();
        if (httpEndpoint.getAuth() && maybeAuth.isPresent()) {
            GeneratedAuthFilesOutput auth = maybeAuth.get();
            requestParameters.add(ParameterSpec.builder(
                            ParameterizedTypeName.get(ClassName.get(Optional.class), auth.getClassName()),
                            AUTH_REQUEST_PARAMETER)
                    .build());
            int startIndex = auth.authSchemeFileOutputs().isEmpty()
                    ? 1
                    : auth.authSchemeFileOutputs().get().size();
            for (int i = startIndex; i < serviceInterfaceMethodParameters.size(); ++i) {
                requestParameters.add(serviceInterfaceMethodParameters.get(i));
            }
        } else {
            requestParameters.addAll(serviceInterfaceMethodParameters);
        }
        return requestParameters;
    }
}
