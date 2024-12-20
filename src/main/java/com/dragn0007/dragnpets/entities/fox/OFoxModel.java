package com.dragn0007.dragnpets.entities.fox;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OFoxModel extends GeoModel<OFox> {

    public enum Variant {
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_red.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_silver.png")),
        SNOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_snow.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public enum DomesticVariant {
        ALBINO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_albino.png")),
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_black.png")),
        BLONDE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_blonde.png")),
        BURGUNDY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_burgundy.png")),
        DARK_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_dark_grey.png")),
        DARK_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_dark_red.png")),
        LEUCISTIC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_leucistic.png")),
        LIGHT_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_light_grey.png")),
        SANDY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/fox_sandy.png"));

        public final ResourceLocation resourceLocation;
        DomesticVariant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static DomesticVariant domesticVariantFromOrdinal(int domesticVariant) { return DomesticVariant.values()[domesticVariant % DomesticVariant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/fox_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OFox object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OFox object) {
        if (object.isDomestic()) {
            return object.getDomesticResource();
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OFox animatable) {
        return ANIMATION;
    }
}

