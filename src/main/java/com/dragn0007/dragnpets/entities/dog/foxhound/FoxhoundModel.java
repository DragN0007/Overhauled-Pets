package com.dragn0007.dragnpets.entities.dog.foxhound;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FoxhoundModel extends GeoModel<Foxhound> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/foxhound.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Foxhound object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Foxhound object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Foxhound animatable) {
        return ANIMATION;
    }
}

