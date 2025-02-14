/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../..";
import * as SeedTrace from "../../../../api";
import * as core from "../../../../core";
import { ErrorInfo } from "./ErrorInfo";
import { RunningSubmissionState } from "./RunningSubmissionState";
import { WorkspaceRunDetails } from "./WorkspaceRunDetails";

export const WorkspaceSubmissionStatus: core.serialization.Schema<
    serializers.WorkspaceSubmissionStatus.Raw,
    SeedTrace.WorkspaceSubmissionStatus
> = core.serialization
    .union("type", {
        stopped: core.serialization.object({}),
        errored: core.serialization.object({
            value: ErrorInfo,
        }),
        running: core.serialization.object({
            value: RunningSubmissionState,
        }),
        ran: WorkspaceRunDetails,
        traced: WorkspaceRunDetails,
    })
    .transform<SeedTrace.WorkspaceSubmissionStatus>({
        transform: (value) => {
            switch (value.type) {
                case "stopped":
                    return SeedTrace.WorkspaceSubmissionStatus.stopped();
                case "errored":
                    return SeedTrace.WorkspaceSubmissionStatus.errored(value.value);
                case "running":
                    return SeedTrace.WorkspaceSubmissionStatus.running(value.value);
                case "ran":
                    return SeedTrace.WorkspaceSubmissionStatus.ran(value);
                case "traced":
                    return SeedTrace.WorkspaceSubmissionStatus.traced(value);
                default:
                    return SeedTrace.WorkspaceSubmissionStatus._unknown(value);
            }
        },
        untransform: ({ _visit, ...value }) => value as any,
    });

export declare namespace WorkspaceSubmissionStatus {
    type Raw =
        | WorkspaceSubmissionStatus.Stopped
        | WorkspaceSubmissionStatus.Errored
        | WorkspaceSubmissionStatus.Running
        | WorkspaceSubmissionStatus.Ran
        | WorkspaceSubmissionStatus.Traced;

    interface Stopped {
        type: "stopped";
    }

    interface Errored {
        type: "errored";
        value: ErrorInfo.Raw;
    }

    interface Running {
        type: "running";
        value: RunningSubmissionState.Raw;
    }

    interface Ran extends WorkspaceRunDetails.Raw {
        type: "ran";
    }

    interface Traced extends WorkspaceRunDetails.Raw {
        type: "traced";
    }
}
