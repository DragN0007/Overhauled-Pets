package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Husk;
import software.bernie.geckolib.model.GeoModel;

public class HuskyModel extends GeoModel<Husky> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/husky/husky_black.png")),
        BLACK_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/husky/husky_black_blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/husky/husky_brown.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/husky/husky_grey.png")),
        GREY_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/husky/husky_grey_blue.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/husky.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Husky object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Husky object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Husky animatable) {
        return ANIMATION;
    }
}

