package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum DogBreed {
    MUTT(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json")),
    AMERICAN_RIDGEBACK(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/american_ridgeback.geo.json")),
    AUSTRALIAN_SHEPHERD(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/australian_shepherd.geo.json")),
    BEAGLE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/beagle.geo.json")),
    BERNESE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bernese_mountain.geo.json")),
    BLOODHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bloodhound.geo.json")),
    BORDER_COLLIE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/border_collie.geo.json")),
    COCKER_SPANIEL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/cocker_spaniel.geo.json")),
    COONHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/coonhound.geo.json")),
    DOBERMAN(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/doberman.geo.json")),
    FOXHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/foxhound.geo.json")),
    HUSKY(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/husky.geo.json")),
    JACK_RUSSELL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/jack_russell.geo.json")),
    LABRADOR(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/labrador.geo.json")),
    PYRENEES(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/pyrenees.geo.json")),
    ROTTWEILER(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/rottweiler.geo.json")),
    WHIPPET(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/whippet.geo.json")),
    PITBULL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json")),
    GERMAN_SHEPHERD(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json")),
    BORZOI(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json")),
    ;

    public final ResourceLocation resourceLocation;

    DogBreed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

    public static DogBreed breedFromOrdinal(int ordinal) {
        return DogBreed.values()[ordinal % DogBreed.values().length];
    }

    public DogBreed next() {
        return DogBreed.values()[(this.ordinal() + 1) % DogBreed.values().length];
    }
}
