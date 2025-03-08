package com.dragn0007.dragnpets.entities.dog.cocker_spaniel;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CockerSpanielModel extends GeoModel<CockerSpaniel> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_brown.png")),
        GOLD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_gold.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_red.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_white.png")),
        LOSTMELODIES(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cocker_spaniel/cocker_spaniel_lostmelodies.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/cocker_spaniel.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cocker_spaniel.animation.json");

    @Override
    public ResourceLocation getModelResource(CockerSpaniel object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(CockerSpaniel object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(CockerSpaniel animatable) {
        return ANIMATION;
    }
}

