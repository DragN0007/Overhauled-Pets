package com.dragn0007.dragnpets.entities.dog.australian_shepherd;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AustralianShepherdModel extends GeoModel<AustralianShepherd> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/australian_shepherd.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(AustralianShepherd object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(AustralianShepherd object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(AustralianShepherd animatable) {
        return ANIMATION;
    }
}

