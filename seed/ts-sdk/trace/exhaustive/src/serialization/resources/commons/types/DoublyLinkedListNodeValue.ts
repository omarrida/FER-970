/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../..";
import * as SeedTrace from "../../../../api";
import * as core from "../../../../core";
import { NodeId } from "./NodeId";

export const DoublyLinkedListNodeValue: core.serialization.ObjectSchema<
    serializers.DoublyLinkedListNodeValue.Raw,
    SeedTrace.DoublyLinkedListNodeValue
> = core.serialization.object({
    nodeId: NodeId,
    val: core.serialization.number(),
    next: NodeId.optional(),
    prev: NodeId.optional(),
});

export declare namespace DoublyLinkedListNodeValue {
    interface Raw {
        nodeId: NodeId.Raw;
        val: number;
        next?: NodeId.Raw | null;
        prev?: NodeId.Raw | null;
    }
}
