package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CollieModel extends GeoModel<Collie> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_black.png")),
        BLACK_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_black_brown.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_grey.png")),
        GREY_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_grey_brown.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_brown.png")),
        BROWN_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_brown_brown.png")),
        FAWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_fawn.png")),
        FAWN_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_fawn_brown.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_red.png")),
        RED_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/border_collie/collie_red_brown.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/border_collie.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/border_collie.animation.json");

    @Override
    public ResourceLocation getModelResource(Collie object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Collie object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Collie animatable) {
        return ANIMATION;
    }
}

