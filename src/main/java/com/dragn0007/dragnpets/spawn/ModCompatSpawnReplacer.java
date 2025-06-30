package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCompatSpawnReplacer {

    /*
    Current Compatiblities:
    -TerraFirmaCraft
     */

    @SubscribeEvent
    public static void onModdedSpawn(EntityJoinLevelEvent event) {

        Random random = new Random();

        // TerraFirmaCraft Wolf -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_WOLVES.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "wolf"))) {

            Entity tfcWolf = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Wolf wolf = EntityType.WOLF.create(event.getLevel());
            if (wolf != null) {
                wolf.copyPosition(tfcWolf);
                wolf.setCustomName(tfcWolf.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcWolf.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(wolf);
                tfcWolf.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Ocelot -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_OCELOTS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "ocelot"))) {

            Entity tfcOcelot = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Ocelot ocelot = EntityType.OCELOT.create(event.getLevel());
            if (ocelot != null) {
                ocelot.copyPosition(tfcOcelot);
                ocelot.setCustomName(tfcOcelot.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcOcelot.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(ocelot);
                tfcOcelot.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Fox -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_FOXES.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "fox"))) {

            Entity tfcFox = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Fox fox = EntityType.FOX.create(event.getLevel());
            if (fox != null) {
                fox.copyPosition(tfcFox);
                fox.setCustomName(tfcFox.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcFox.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(fox);
                tfcFox.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Cat -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_CATS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "cat"))) {

            Entity tfcCat = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Cat cat = EntityType.CAT.create(event.getLevel());
            if (cat != null) {
                cat.copyPosition(tfcCat);
                cat.setCustomName(tfcCat.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcCat.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(cat);
                tfcCat.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Dog -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_CATS.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "dog"))) {

            Entity tfcDog = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            Cat dog = EntityType.CAT.create(event.getLevel());
            if (dog != null) {
                dog.copyPosition(tfcDog);
                dog.setCustomName(tfcDog.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcDog.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(dog);
                tfcDog.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        // TerraFirmaCraft Tropical Fish -> Vanilla (so it can be converted into an O-Variant)
        if (PetsOverhaulCommonConfig.REPLACE_TROPICAL_FISH.get() &&
                ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType()).equals(new ResourceLocation("tfc", "tropical_fish"))) {

            Entity tfcFish = event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            TropicalFish tropicalFish = EntityType.TROPICAL_FISH.create(event.getLevel());
            if (tropicalFish != null) {
                tropicalFish.copyPosition(tfcFish);
                tropicalFish.setCustomName(tfcFish.getCustomName());

                if (event.getLevel().isClientSide) {
                    tfcFish.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(tropicalFish);
                tfcFish.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }



    }
}