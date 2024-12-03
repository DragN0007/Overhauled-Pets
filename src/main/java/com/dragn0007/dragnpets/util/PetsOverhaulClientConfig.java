package com.dragn0007.dragnpets.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class PetsOverhaulClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue COLLAR_GENDER_INDICATOR;

    static {
        BUILDER.push("Client");
        COLLAR_GENDER_INDICATOR = BUILDER.comment("Should collars for cats and dogs have a small, colored gender indicator?")
                .define("Collar Gender Indicators", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
