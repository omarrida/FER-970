/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../../../..";
import * as Fiddle from "../../../../../../api";
import * as core from "../../../../../../core";
import { ObjectWithOptionalField } from "./ObjectWithOptionalField";

export const NestedObjectWithRequiredField: core.serialization.ObjectSchema<
    serializers.types.NestedObjectWithRequiredField.Raw,
    Fiddle.types.NestedObjectWithRequiredField
> = core.serialization.object({
    string: core.serialization.string(),
    nestedObject: core.serialization.property("NestedObject", ObjectWithOptionalField),
});

export declare namespace NestedObjectWithRequiredField {
    interface Raw {
        string: string;
        NestedObject: ObjectWithOptionalField.Raw;
    }
}
