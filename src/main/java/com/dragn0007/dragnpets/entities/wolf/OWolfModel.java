package com.dragn0007.dragnpets.entities.wolf;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OWolfModel extends GeoModel<OWolf> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/black.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/brown.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/chocolate.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/cream.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/grey.png")),
        MAHOGANY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/mahogany.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/red.png")),
        TIMBER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/timber.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/o_wolf.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/o_wolf.animation.json");

    @Override
    public ResourceLocation getModelResource(OWolf object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OWolf object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OWolf animatable) {
        return ANIMATION;
    }
}

