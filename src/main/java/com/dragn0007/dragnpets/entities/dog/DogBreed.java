package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.horse.HorseBreed;
import net.minecraft.resources.ResourceLocation;

public enum DogBreed {
    MUTT(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/o_dog.geo.json")),
    AUSTRALIAN_SHEPHERD(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/australian_shepherd.geo.geo.json")),
    BEAGLE(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/beagle.geo.json")),
    BERNESE(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/bernese.geo.json")),
    BLOODHOUND(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/bloodhound.geo.json")),
    BORDER_COLLIE(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/border_collie.geo.json")),
    COCKER_SPANIEL(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/cocker_spaniel.geo.json")),
    COONHOUND(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/coonhound.geo.json")),
    DOBERMAN(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/doberman.geo.json")),
    FOXHOUND(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/foxhound.geo.json")),
    HUSKY(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/husky.geo.json")),
    JACK_RUSSELL(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/rack_russell.geo.json")),
    LABRADOR(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/labrador.geo.json")),
    PYRENEES(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/pyrenees.geo.json")),
    ROTTWEILER(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/rottweiler.geo.json")),
    WHIPPET(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/whippet.geo.json")),
    PITBULL(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/pitbull.geo.json")),
    GERMAN_SHEPHERD(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/german_shepherd.geo.json")),
    BORZOI(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/borzoi.geo.json")),
    AMERICAN_RIDGEBACK(new ResourceLocation(LivestockOverhaul.MODID, "geo/dog/american_ridgeback.geo.json")),
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
