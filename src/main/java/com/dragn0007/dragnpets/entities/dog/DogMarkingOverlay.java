package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum DogMarkingOverlay {
        NONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/none.png")),
        BLOODHOUND_SPOT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/bloodhound_spot.png")),
        PURE_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/pure_white.png")),
        RIDGE_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/ridge_splash.png")),
        RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/rust.png")),
        MOUNTAIN_RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/mountain_rust.png")),
        AUSTRALIAN_RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/australian_rust.png")),
        AUSTRALIAN_MERLE_RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/australian_merle_rust.png")),
        COLLIE_RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/collie_rust.png")),
        COLLIE_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/collie_splash.png")),
        SPANIEL_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/australian_rust.png")),
        SPANIEL_LM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/spaniel_lm.png")),
        HUSKY_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/husky_splash.png")),
        SEAL_BRINDLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/seal_brindle.png")),
        SILVER_BRINDLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/silver_brindle.png")),
        BEAGLE_SPOT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/beagle_spot.png")),
        COONHOUND_SPECKLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/coonhound_speckle.png")),
        FOXHOUND_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/foxhound_splash.png")),
        RUSSEL_FEW_SPOT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/russel_few_spot.png")),
        ;

        public final ResourceLocation resourceLocation;
        DogMarkingOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static DogMarkingOverlay overlayFromOrdinal(int overlay) { return DogMarkingOverlay.values()[overlay % DogMarkingOverlay.values().length];
        }
}
