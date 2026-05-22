package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum DogBreed {
    MUTT(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json")), //0
    AMERICAN_RIDGEBACK(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/american_ridgeback.geo.json")), //1
    AUSTRALIAN_SHEPHERD(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/australian_shepherd.geo.json")), //2
    BEAGLE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/beagle.geo.json")), //3
    BERNESE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bernese_mountain.geo.json")), //4
    BLOODHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bloodhound.geo.json")), //5
    BORDER_COLLIE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/border_collie.geo.json")), //6
    COCKER_SPANIEL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/cocker_spaniel.geo.json")), //7
    COONHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/coonhound.geo.json")), //8
    DOBERMAN(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/doberman.geo.json")), //9
    FOXHOUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/foxhound.geo.json")), //10
    HUSKY(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/husky.geo.json")), //11
    JACK_RUSSELL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/jack_russell.geo.json")), //12
    LABRADOR(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/labrador.geo.json")), //13
    PYRENEES(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/pyrenees.geo.json")), //14
    ROTTWEILER(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/rottweiler.geo.json")), //15
    WHIPPET(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/whippet.geo.json")), //16
    PITBULL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/pitbull.geo.json")), //17
    GERMAN_SHEPHERD(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/german_shepherd.geo.json")), //18
    BORZOI(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/borzoi.geo.json")), //19
    NEWFOUNDLAND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/newfoundland.geo.json")), //20
    KANGAL(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/kangal_shepherd.geo.json")), //21
    BELGIAN_MALINOIS(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/belgian_shepherd.geo.json")), //22
    TIBETAN_MASTIFF(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/tibetan_mastiff.geo.json")), //23
    COLLIE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/collie.geo.json")), //24
    WOLFDOG(new ResourceLocation(PetsOverhaul.MODID, "geo/o_wolf.geo.json")), //25
    DALMATIAN(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/dalmatian.geo.json")), //26
    DACHSHUND(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/dachshund.geo.json")), //27
    JAGDTERRIER(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/jagdterrier.geo.json")), //28
    BULL_TERRIER(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bull_terrier.geo.json")), //29
    POODLE(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/poodle.geo.json")), //30
    CHIHUAHUA(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/chihuahua.geo.json")), //31
    XOLOITZCUINTLI(new ResourceLocation(PetsOverhaul.MODID, "geo/dog/xoloitzcuintli.geo.json")), //32
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
