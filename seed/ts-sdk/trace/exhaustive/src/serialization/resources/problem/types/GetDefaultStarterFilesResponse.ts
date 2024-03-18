/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../..";
import * as SeedTrace from "../../../../api";
import * as core from "../../../../core";
import { ProblemFiles } from "./ProblemFiles";
import { Language } from "../../commons/types/Language";

export const GetDefaultStarterFilesResponse: core.serialization.ObjectSchema<
    serializers.GetDefaultStarterFilesResponse.Raw,
    SeedTrace.GetDefaultStarterFilesResponse
> = core.serialization.object({
    files: core.serialization.record(Language, ProblemFiles.optional()),
});

export declare namespace GetDefaultStarterFilesResponse {
    interface Raw {
        files: Record<Language.Raw, ProblemFiles.Raw | null | undefined>;
    }
}
