package com.dragn0007.dragnpets.entities.dog.australian_shepherd;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AustralianShepherdModel extends GeoModel<AustralianShepherd> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_blue.png")),
        BLUE_MERLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_blue_merle.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_red.png")),
        RED_MERLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_red_merle.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_white.png")),
        WHITE_MERLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/ashepherd/ashepherd_white_merle.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/ashepherd.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/ashepherd.animation.json");

    @Override
    public ResourceLocation getModelResource(AustralianShepherd object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AustralianShepherd object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(AustralianShepherd animatable) {
        return ANIMATION;
    }
}

