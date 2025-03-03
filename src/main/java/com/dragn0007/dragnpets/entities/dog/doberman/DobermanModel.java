package com.dragn0007.dragnpets.entities.dog.doberman;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DobermanModel extends GeoModel<Doberman> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_black.png")),
        BLACK_NATURAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_black_natural.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_blue.png")),
        BLUE_NATURAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_blue_natural.png")),
        FAWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_fawn.png")),
        FAWN_NATURAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_fawn_natural.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_red.png")),
        RED_NATURAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/doberman/doberman_red_natural.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/doberman.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/doberman.animation.json");

    @Override
    public ResourceLocation getModelResource(Doberman object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Doberman object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Doberman animatable) {
        return ANIMATION;
    }
}

