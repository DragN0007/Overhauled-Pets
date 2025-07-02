package com.dragn0007.dragnpets.entities.dog.doberman;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DobermanModel extends GeoModel<Doberman> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/doberman.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Doberman object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Doberman object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Doberman animatable) {
        return ANIMATION;
    }
}

