import { getTextOfTsKeyword, getTextOfTsNode, maybeAddDocs } from "@fern-typescript/commons";
import { AugmentedService } from "@fern-typescript/commons-v2";
import { SdkFile } from "@fern-typescript/sdk-declaration-handler";
import { Scope, ts } from "ts-morph";
import { Endpoint } from "./endpoints/Endpoint";
import { WrappedServiceGenerator } from "./WrappedServiceGenerator";

export declare namespace Client {
    export interface Init {
        service: AugmentedService;
        serviceClassName: string;
    }
}

export class Client {
    private static OPTIONS_INTERFACE_NAME = "Options";
    private static OPTIONS_PRIVATE_MEMBER = "options";
    public static ORIGIN_OPTIONS_PROPERTY_NAME = "_origin";

    private service: AugmentedService;
    private serviceClassName: string;

    constructor({ service, serviceClassName }: Client.Init) {
        this.service = service;
        this.serviceClassName = serviceClassName;
    }

    public generate(file: SdkFile, endpoints: Endpoint[]): void {
        // const serviceInterface = file.sourceFile.addInterface({
        //     name: this.serviceClassName,
        //     isExported: true,
        // });

        const serviceModule = file.sourceFile.addModule({
            name: this.serviceClassName,
            isExported: true,
            hasDeclareKeyword: true,
        });

        const optionsInterface = serviceModule.addInterface({
            name: Client.OPTIONS_INTERFACE_NAME,
            properties: [
                {
                    name: Client.ORIGIN_OPTIONS_PROPERTY_NAME,
                    type: getTextOfTsKeyword(ts.SyntaxKind.StringKeyword),
                },
            ],
        });

        optionsInterface.addProperties(file.authSchemes.getProperties());

        const serviceClass = file.sourceFile.addClass({
            name: this.serviceClassName,
            isExported: true,
        });
        maybeAddDocs(serviceClass, this.service.originalService?.docs);

        serviceClass.addConstructor({
            parameters: [
                {
                    name: Client.OPTIONS_PRIVATE_MEMBER,
                    isReadonly: true,
                    scope: Scope.Private,
                    type: getTextOfTsNode(
                        ts.factory.createTypeReferenceNode(
                            ts.factory.createQualifiedName(
                                ts.factory.createIdentifier(serviceModule.getName()),
                                ts.factory.createIdentifier(optionsInterface.getName())
                            )
                        )
                    ),
                },
            ],
        });

        for (const endpoint of endpoints) {
            // serviceInterface.addMethod(endpoint.getSignature(file));
            serviceClass.addMethod(endpoint.getImplementation(file));
        }

        for (const wrappedService of this.service.wrappedServices) {
            const wrappedServiceGenerator = new WrappedServiceGenerator({
                wrappedService,
            });
            // wrappedServiceGenerator.addToServiceInterface(serviceInterface, file);
            wrappedServiceGenerator.addToServiceClass(serviceClass, file);
        }
    }

    public static getAuthHeaders(file: SdkFile): ts.ObjectLiteralElementLike[] {
        return file.authSchemes.getHeaders(this.getReferenceToOptions());
    }

    public static getReferenceToOrigin(): ts.Expression {
        return this.getReferenceToOption(Client.ORIGIN_OPTIONS_PROPERTY_NAME);
    }

    public static getReferenceToOptions(): ts.Expression {
        return ts.factory.createPropertyAccessExpression(ts.factory.createThis(), Client.OPTIONS_PRIVATE_MEMBER);
    }

    private static getReferenceToOption(option: string): ts.Expression {
        return ts.factory.createPropertyAccessExpression(this.getReferenceToOptions(), option);
    }

    public static instatiate({
        referenceToClient,
        referenceToOptions,
    }: {
        referenceToClient: ts.Expression;
        referenceToOptions: ts.Expression;
    }): ts.NewExpression {
        return ts.factory.createNewExpression(referenceToClient, undefined, [referenceToOptions]);
    }
}
