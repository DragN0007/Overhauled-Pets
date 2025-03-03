package com.dragn0007.dragnpets.entities.cat.maine_coon;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MaineCoonModel extends GeoModel<MaineCoon> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/maine_coon/maine_coon_black.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/maine_coon/maine_coon_grey.png")),
        ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/maine_coon/maine_coon_orange.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/maine_coon/maine_coon_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/maine_coon.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(MaineCoon object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(MaineCoon object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(MaineCoon animatable) {
        return ANIMATION;
    }
}

