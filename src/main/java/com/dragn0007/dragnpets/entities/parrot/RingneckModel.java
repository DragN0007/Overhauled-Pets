package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RingneckModel extends GeoModel<Ringneck> {

    public enum Variant {
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_blue.png")),
        CINNAMON(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_cinnamon.png")),
        CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_cyan.png")),
        GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_green.png")),
        LIGHT_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_light_blue.png")),
        POWDER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_powder.png")),
        SLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_slate.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_yellow.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/ringneck.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/ringneck.animation.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/parrot_baby.png");

    @Override
    public ResourceLocation getModelResource(Ringneck object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Ringneck object) {
        if (object.isBaby()) {
            return BABY_TEXTURE;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Ringneck animatable) {
        return ANIMATION;
    }
}

