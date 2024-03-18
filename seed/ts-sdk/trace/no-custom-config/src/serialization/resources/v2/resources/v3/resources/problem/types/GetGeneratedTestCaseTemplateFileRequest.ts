/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../../../../../..";
import * as SeedTrace from "../../../../../../../../api";
import * as core from "../../../../../../../../core";
import { TestCaseTemplate } from "./TestCaseTemplate";

export const GetGeneratedTestCaseTemplateFileRequest: core.serialization.ObjectSchema<
    serializers.v2.v3.GetGeneratedTestCaseTemplateFileRequest.Raw,
    SeedTrace.v2.v3.GetGeneratedTestCaseTemplateFileRequest
> = core.serialization.object({
    template: TestCaseTemplate,
});

export declare namespace GetGeneratedTestCaseTemplateFileRequest {
    interface Raw {
        template: TestCaseTemplate.Raw;
    }
}
