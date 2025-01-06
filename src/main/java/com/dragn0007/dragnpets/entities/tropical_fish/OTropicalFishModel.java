package com.dragn0007.dragnpets.entities.tropical_fish;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.TropicalFish;
import software.bernie.geckolib.model.GeoModel;

public class OTropicalFishModel extends GeoModel<OTropicalFish> {

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/brown.png")),
        CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/cyan.png")),
        GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/green.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/grey.png")),
        LIGHT_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/light_blue.png")),
        LIGHT_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/light_grey.png")),
        LIME(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/lime.png")),
        MAGENTA(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/magenta.png")),
        ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/orange.png")),
        PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/pink.png")),
        PURPLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/purple.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/red.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/white.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/yellow.png")),

        FINNED_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_black.png")),
        FINNED_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_blue.png")),
        FINNED_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_brown.png")),
        FINNED_CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_cyan.png")),
        FINNED_GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_green.png")),
        FINNED_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_grey.png")),
        FINNED_LIGHT_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_light_blue.png")),
        FINNED_LIGHT_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_light_grey.png")),
        FINNED_LIME(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_lime.png")),
        FINNED_MAGENTA(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_magenta.png")),
        FINNED_ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_orange.png")),
        FINNED_PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_pink.png")),
        FINNED_PURPLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_purple.png")),
        FINNED_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_red.png")),
        FINNED_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_white.png")),
        FINNED_YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/betta_yellow.png")),

        UNFINNED_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_black.png")),
        UNFINNED_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_blue.png")),
        UNFINNED_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_brown.png")),
        UNFINNED_CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_cyan.png")),
        UNFINNED_GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_green.png")),
        UNFINNED_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_grey.png")),
        UNFINNED_LIGHT_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_light_blue.png")),
        UNFINNED_LIGHT_GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_light_grey.png")),
        UNFINNED_LIME(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_lime.png")),
        UNFINNED_MAGENTA(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_magenta.png")),
        UNFINNED_ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_orange.png")),
        UNFINNED_PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_pink.png")),
        UNFINNED_PURPLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_purple.png")),
        UNFINNED_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_red.png")),
        UNFINNED_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_white.png")),
        UNFINNED_YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/clownfish_yellow.png")),
        ;

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation ANIMATION = new ResourceLocation(LivestockOverhaul.MODID, "animations/o_fish.animation.json");

    @Override
    public ResourceLocation getModelResource(OTropicalFish object) {
        return object.getSpeciesResource();
    }

    @Override
    public ResourceLocation getTextureResource(OTropicalFish object) {
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(OTropicalFish animatable) {
        return ANIMATION;
    }
}

