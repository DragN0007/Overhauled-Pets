package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeHitter {

    public static final ResourceKey<BiomeModifier> WOLF = registerKey("wolf");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

//        context.register(WOLF, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
//                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
//                List.of(new MobSpawnSettings.SpawnerData(POEntityTypes.O_WOLF_ENTITY.get(),
//                        10,
//                        1,
//                        3
//                ))));

    }

    public static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(PetsOverhaul.MODID, name));
    }
}