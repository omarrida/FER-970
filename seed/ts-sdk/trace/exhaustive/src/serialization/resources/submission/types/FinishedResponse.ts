/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../..";
import * as SeedTrace from "../../../../api";
import * as core from "../../../../core";
import { SubmissionId } from "./SubmissionId";

export const FinishedResponse: core.serialization.ObjectSchema<
    serializers.FinishedResponse.Raw,
    SeedTrace.FinishedResponse
> = core.serialization.object({
    submissionId: SubmissionId,
});

export declare namespace FinishedResponse {
    interface Raw {
        submissionId: SubmissionId.Raw;
    }
}
