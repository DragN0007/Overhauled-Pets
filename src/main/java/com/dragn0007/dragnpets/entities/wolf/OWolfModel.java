package com.dragn0007.dragnpets.entities.wolf;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OWolfModel extends GeoModel<OWolf> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_black.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_brown.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_grey.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_red.png")),
        TIMBER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_timber.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/wolf_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/wolf_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/wolf_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OWolf object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OWolf object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OWolf animatable) {
        return ANIMATION;
    }
}

