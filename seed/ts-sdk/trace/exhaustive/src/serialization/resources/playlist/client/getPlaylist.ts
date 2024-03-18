/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../..";
import * as SeedTrace from "../../../../api";
import * as core from "../../../../core";
import { PlaylistIdNotFoundErrorBody } from "../types/PlaylistIdNotFoundErrorBody";

export const Error: core.serialization.Schema<
    serializers.playlist.getPlaylist.Error.Raw,
    SeedTrace.playlist.getPlaylist.Error
> = core.serialization
    .union("errorName", {
        PlaylistIdNotFoundError: core.serialization.object({
            content: PlaylistIdNotFoundErrorBody,
        }),
        UnauthorizedError: core.serialization.object({}),
    })
    .transform<SeedTrace.playlist.getPlaylist.Error>({
        transform: (value) => {
            switch (value.errorName) {
                case "PlaylistIdNotFoundError":
                    return SeedTrace.playlist.getPlaylist.Error.playlistIdNotFoundError(value.content);
                case "UnauthorizedError":
                    return SeedTrace.playlist.getPlaylist.Error.unauthorizedError();
            }
        },
        untransform: ({ _visit, ...value }) => value as any,
    });

export declare namespace Error {
    type Raw = Error.PlaylistIdNotFoundError | Error.UnauthorizedError;

    interface PlaylistIdNotFoundError {
        errorName: "PlaylistIdNotFoundError";
        content: PlaylistIdNotFoundErrorBody.Raw;
    }

    interface UnauthorizedError {
        errorName: "UnauthorizedError";
    }
}
