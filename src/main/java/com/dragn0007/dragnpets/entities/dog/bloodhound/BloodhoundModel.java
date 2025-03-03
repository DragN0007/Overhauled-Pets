package com.dragn0007.dragnpets.entities.dog.bloodhound;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodhoundModel extends GeoModel<Bloodhound> {

    public enum Variant {
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/bloodhound/bloodhound_brown.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/bloodhound/bloodhound_red.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/bloodhound.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Bloodhound object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Bloodhound object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Bloodhound animatable) {
        return ANIMATION;
    }
}

