package com.dragn0007.dragnpets.entities.dog.bloodhound;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodhoundModel extends GeoModel<Bloodhound> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bloodhound.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Bloodhound object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Bloodhound object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Bloodhound animatable) {
        return ANIMATION;
    }
}

