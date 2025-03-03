package com.dragn0007.dragnpets.entities.dog.pyrenees;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PyreneesModel extends GeoModel<Pyrenees> {

    public enum Variant {
        DEFAULT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/pyrenees/pyrenees.png")),
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/pyrenees/pyrenees_black.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/pyrenees/pyrenees_grey.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/pyrenees.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(Pyrenees object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Pyrenees object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Pyrenees animatable) {
        return ANIMATION;
    }
}

