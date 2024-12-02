package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record CreatureSpawnGeneration(HolderSet<Biome> biomes) implements BiomeModifier {



    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase == Phase.ADD && biomes.contains(biome)) {
            List<MobSpawnSettings.SpawnerData> spawner = builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE);


        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return PetsOverhaul.SPAWN_CODEC.get();
    }
}
