package com.dragn0007.dragnpets.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class PetsOverhaulCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue REPLACE_WOLVES;
    public static final ForgeConfigSpec.BooleanValue REPLACE_OCELOTS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_FOXES;
    public static final ForgeConfigSpec.BooleanValue REPLACE_AXOLOTLS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_PARROTS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_CATS;
    public static final ForgeConfigSpec.BooleanValue REPLACE_TROPICAL_FISH;

    static {
        BUILDER.push("Spawning");

        REPLACE_WOLVES = BUILDER.comment("Should wolves be replaced by O-Wolves?")
                .define("Replace Wolves", true);

        REPLACE_OCELOTS = BUILDER.comment("Should ocelots be replaced by O-Ocelots?")
                .define("Replace Ocelots", true);

        REPLACE_FOXES = BUILDER.comment("Should foxes be replaced by O-Foxes?")
                .define("Replace Foxes", true);

        REPLACE_AXOLOTLS = BUILDER.comment("Should axolotls be replaced by O-Axolotls?")
                .define("Replace Axolotls", true);

        REPLACE_PARROTS = BUILDER.comment("Should parrots be replaced by O-Parrots?")
                .define("Replace Parrots", true);

        REPLACE_CATS = BUILDER.comment("Should cats be replaced by O-Cats?")
                .define("Replace Cats", true);

        REPLACE_TROPICAL_FISH = BUILDER.comment("Should tropical fish be replaced by O-Tropical Fish?")
                .define("Replace Tropical Fish", true);
        BUILDER.pop();


        BUILDER.push("Miscellaneous");



        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
