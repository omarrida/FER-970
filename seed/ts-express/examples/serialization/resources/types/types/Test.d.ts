/**
 * This file was auto-generated by Fern from our API Definition.
 */
import * as serializers from "../../..";
import * as SeedExamples from "../../../../api";
import * as core from "../../../../core";
export declare const Test: core.serialization.Schema<serializers.Test.Raw, SeedExamples.Test>;
export declare namespace Test {
    type Raw = Test.And | Test.Or;
    interface And {
        type: "and";
        value: boolean;
    }
    interface Or {
        type: "or";
        value: boolean;
    }
}
