package com.dragn0007.dragnpets.entities.dog.bernese;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BerneseModel extends GeoModel<Bernese> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bernese_mountain.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Bernese object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Bernese object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Bernese animatable) {
        return ANIMATION;
    }
}

