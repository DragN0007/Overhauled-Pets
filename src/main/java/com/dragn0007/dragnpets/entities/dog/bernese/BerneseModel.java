package com.dragn0007.dragnpets.entities.dog.bernese;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BerneseModel extends GeoModel<Bernese> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/bernese/bernese_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/bernese/bernese_blue.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/bernese.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Bernese object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Bernese object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Bernese animatable) {
        return ANIMATION;
    }
}

