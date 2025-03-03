package com.dragn0007.dragnpets.entities.cat.kornish_rex;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KornishRexModel extends GeoModel<KornishRex> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_brown.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_cream.png")),
        DARK_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_dark_brown.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_grey.png")),
        ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_orange.png")),
        RAGDOLL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_ragdoll.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/cat_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/kornish_rex.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(KornishRex object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(KornishRex object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(KornishRex animatable) {
        return ANIMATION;
    }
}

