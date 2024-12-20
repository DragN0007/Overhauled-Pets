package com.dragn0007.dragnpets.entities.ocelot;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OOcelotModel extends GeoModel<OOcelot> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/ocelot_black.png")),
        BLACK_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/ocelot_black_spotted.png")),
        DEFAULT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/ocelot.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/ocelot_grey.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/ocelot_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/ocelot_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cat_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OOcelot object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OOcelot object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OOcelot animatable) {
        return ANIMATION;
    }
}

