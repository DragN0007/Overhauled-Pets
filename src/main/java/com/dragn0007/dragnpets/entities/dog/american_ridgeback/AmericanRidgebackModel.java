package com.dragn0007.dragnpets.entities.dog.american_ridgeback;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AmericanRidgebackModel extends GeoModel<AmericanRidgeback> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/american_ridgeback.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(AmericanRidgeback object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AmericanRidgeback object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(AmericanRidgeback animatable) {
        return ANIMATION;
    }
}

