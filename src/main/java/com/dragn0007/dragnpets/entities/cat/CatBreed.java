package com.dragn0007.dragnpets.entities.cat;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum CatBreed {
    MUTT(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/o_cat.geo.json")),
    KORNISH_REX(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/kornish_rex.geo.json")),
    MAINE_COON(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/maine_coon.geo.json")),
    MANX(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/manx.geo.json")),
    JAPANESE_BOBTAIL(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/japanese_bobtail.geo.json")),
    NORWEGIAN_FOREST(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/norwegian_forest.geo.json")),
    ORIENTAL_LONGHAIR(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/oriental_longhair.geo.json")),
    ORIENTAL_SHORTHAIR(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/oriental_shorthair.geo.json")),
    RAGDOLL(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/ragdoll.geo.json")),
    RUSSIAN_BLUE(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/o_cat.geo.json")),
    ABYSSINIAN(new ResourceLocation(PetsOverhaul.MODID, "geo/cat/o_cat.geo.json")),
    ;

    public final ResourceLocation resourceLocation;

    CatBreed(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

    public static CatBreed breedFromOrdinal(int ordinal) {
        return CatBreed.values()[ordinal % CatBreed.values().length];
    }

    public CatBreed next() {
        return CatBreed.values()[(this.ordinal() + 1) % CatBreed.values().length];
    }
}
