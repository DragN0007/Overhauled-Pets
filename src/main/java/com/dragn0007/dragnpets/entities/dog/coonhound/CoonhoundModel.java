package com.dragn0007.dragnpets.entities.dog.coonhound;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CoonhoundModel extends GeoModel<Coonhound> {

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/bloodhound.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Coonhound object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Coonhound object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(Coonhound animatable) {
        return ANIMATION;
    }
}

