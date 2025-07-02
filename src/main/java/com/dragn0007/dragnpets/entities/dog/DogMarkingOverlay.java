package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.marking_layer.EquineMarkingOverlay;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;

public enum DogMarkingOverlay {
        NONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/none.png")),
        BLOODHOUND_SPOT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/bloodhound_spot.png")),
        PURE_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/pure_white.png")),
        RIDGE_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/ridge_splash.png")),
        RUST(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/overlay/rust.png")),
        ;

        public final ResourceLocation resourceLocation;
        DogMarkingOverlay(ResourceLocation resourceLocation) {
                this.resourceLocation = resourceLocation;
        }

        public static DogMarkingOverlay overlayFromOrdinal(int overlay) { return DogMarkingOverlay.values()[overlay % DogMarkingOverlay.values().length];
        }
}
