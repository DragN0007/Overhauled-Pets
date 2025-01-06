package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CockatielModel extends GeoModel<Cockatiel> {

    public enum Variant {
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_grey.png")),
        PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_pink.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/cockatiel.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cockatiel.animation.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/parrot_baby.png");

    @Override
    public ResourceLocation getModelResource(Cockatiel object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Cockatiel object) {
        if (object.isBaby()) {
            return BABY_TEXTURE;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Cockatiel animatable) {
        return ANIMATION;
    }
}

