package com.dragn0007.dragnpets.entities.fox;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OFoxModel extends GeoModel<OFox> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/brown.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/chocolate.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/cream.png")),
        GOLD_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/gold_red.png")),
        LILAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/lilac.png")),
        MAHOGANY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/mahogany.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/red.png")),
        SEAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/seal.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/silver.png")),
        TAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/tan.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/o_fox.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/o_fox.animation.json");

    @Override
    public ResourceLocation getModelResource(OFox object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OFox object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OFox animatable) {
        return ANIMATION;
    }
}

