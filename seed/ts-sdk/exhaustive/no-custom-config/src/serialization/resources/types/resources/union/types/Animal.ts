/**
 * This file was auto-generated by Fern from our API Definition.
 */

import * as serializers from "../../../../..";
import * as SeedExhaustive from "../../../../../../api";
import * as core from "../../../../../../core";
import { Dog } from "./Dog";
import { Cat } from "./Cat";

export const Animal: core.serialization.Schema<serializers.types.Animal.Raw, SeedExhaustive.types.Animal> =
    core.serialization
        .union("animal", {
            dog: Dog,
            cat: Cat,
        })
        .transform<SeedExhaustive.types.Animal>({
            transform: (value) => value,
            untransform: (value) => value,
        });

export declare namespace Animal {
    type Raw = Animal.Dog | Animal.Cat;

    interface Dog extends Dog.Raw {
        animal: "dog";
    }

    interface Cat extends Cat.Raw {
        animal: "cat";
    }
}
