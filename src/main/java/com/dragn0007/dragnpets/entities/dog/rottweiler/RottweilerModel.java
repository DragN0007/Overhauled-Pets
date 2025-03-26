package com.dragn0007.dragnpets.entities.dog.rottweiler;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RottweilerModel extends GeoModel<Rottweiler> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/rottweiler/rottweiler_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/rottweiler/rottweiler_blue.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/rottweiler/rottweiler_red.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/rottweiler.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Rottweiler object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Rottweiler object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Rottweiler animatable) {
        return ANIMATION;
    }
}

