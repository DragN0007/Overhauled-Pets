package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LabradorModel extends GeoModel<Labrador> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/black.png")),
        BLONDE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/blonde.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/chocolate.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/red.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/silver.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/labrador/yellow.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/labrador.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Labrador object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Labrador object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Labrador animatable) {
        return ANIMATION;
    }
}

