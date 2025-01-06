package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MacawModel extends GeoModel<Macaw> {

    public enum Variant {
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/macaw/macaw_blue.png")),
        CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/macaw/macaw_cyan.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/macaw/macaw_red.png")),
        WATERMELON(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/macaw/macaw_watermelon.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/macaw.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/ringneck.animation.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/parrot_baby.png");

    @Override
    public ResourceLocation getModelResource(Macaw object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Macaw object) {
        if (object.isBaby()) {
            return BABY_TEXTURE;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Macaw animatable) {
        return ANIMATION;
    }
}

