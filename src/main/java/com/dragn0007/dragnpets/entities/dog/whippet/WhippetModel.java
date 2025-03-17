package com.dragn0007.dragnpets.entities.dog.whippet;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WhippetModel extends GeoModel<Whippet> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_brown.png")),
        FAWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_fawn.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_grey.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_red.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_silver.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_white.png")),
        LOSTMELODIES(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/whippet/whippet_lostmelodies.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/whippet.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/whippet.animation.json");

    @Override
    public ResourceLocation getModelResource(Whippet object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Whippet object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Whippet animatable) {
        return ANIMATION;
    }
}

