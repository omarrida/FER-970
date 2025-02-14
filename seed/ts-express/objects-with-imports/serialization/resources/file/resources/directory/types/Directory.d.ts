/**
 * This file was auto-generated by Fern from our API Definition.
 */
import * as serializers from "../../../../..";
import * as SeedObjectsWithImports from "../../../../../../api";
import * as core from "../../../../../../core";
export declare const Directory: core.serialization.ObjectSchema<serializers.file.Directory.Raw, SeedObjectsWithImports.file.Directory>;
export declare namespace Directory {
    interface Raw {
        name: string;
        files?: serializers.File_.Raw[] | null;
        directories?: serializers.file.Directory.Raw[] | null;
    }
}
