package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnlivestock.entities.cow.OCowHornLayer;
import com.dragn0007.dragnlivestock.entities.cow.OCowMarkingLayer;
import com.dragn0007.dragnlivestock.entities.cow.OCowModel;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxMarkingLayer;
import com.dragn0007.dragnpets.entities.fox.OFoxModel;
import com.dragn0007.dragnpets.entities.fox.OFoxRender;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotModel;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.entities.wolf.OWolfModel;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {

    // This class falls under the LGPL license, as stated in the CODE_LICENSE.txt
    // Some of this code was referenced from Realistic Horse Genetics. Please check them out, too! :)
    // https://github.com/sekelsta/horse-colors  |  https://www.curseforge.com/minecraft/mc-mods/realistic-horse-genetics

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {

        //Wolf
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_WOLVES.get() && event.getEntity() instanceof Wolf) {
            Wolf vanillaWolf = (Wolf) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OWolf oWolf = EntityTypes.O_WOLF_ENTITY.get().create(event.getLevel());
            if (oWolf != null) {
                oWolf.copyPosition(vanillaWolf);

                oWolf.setCustomName(vanillaWolf.getCustomName());
                oWolf.setAge(vanillaWolf.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(OWolfModel.Variant.values().length);
                oWolf.setVariant(randomVariant);

                int randomGender = event.getLevel().getRandom().nextInt(OWolf.Gender.values().length);
                oWolf.setGender(randomGender);

                if (event.getLevel().isClientSide) {
                    vanillaWolf.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oWolf);
                vanillaWolf.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Ocelot
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_OCELOTS.get() && event.getEntity() instanceof Ocelot) {
            Ocelot vanillaOcelot = (Ocelot) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OOcelot oOcelot = EntityTypes.O_OCELOT_ENTITY.get().create(event.getLevel());
            if (oOcelot != null) {
                oOcelot.copyPosition(vanillaOcelot);

                oOcelot.setCustomName(vanillaOcelot.getCustomName());
                oOcelot.setAge(vanillaOcelot.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(OOcelotModel.Variant.values().length);
                oOcelot.setVariant(randomVariant);

                int randomGender = event.getLevel().getRandom().nextInt(OOcelot.Gender.values().length);
                oOcelot.setGender(randomGender);

                if (event.getLevel().isClientSide) {
                    vanillaOcelot.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oOcelot);
                vanillaOcelot.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Fox
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_FOXES.get() && event.getEntity() instanceof Fox) {
            Fox vanillaFox = (Fox) event.getEntity();
            OFox oFox = EntityTypes.O_FOX_ENTITY.get().create(event.getLevel());

            if (event.getLevel().isClientSide) {
                return;
            }

//            if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SNOWY_TAIGA) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.TAIGA) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.OLD_GROWTH_PINE_TAIGA) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.OLD_GROWTH_SPRUCE_TAIGA) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_CONIFEROUS) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD) ||
//                    event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_SNOWY)) {
//
//                if (oFox != null) {
//                    oFox.copyPosition(vanillaFox);
//                    event.getLevel().addFreshEntity(oFox);
//
//                    int snowyVariant = 2;
//                    oFox.setVariant(snowyVariant);
//
//                    if (event.getLevel().isClientSide) {
//                        vanillaFox.remove(Entity.RemovalReason.DISCARDED);
//                    }
//
//                    event.getLevel().addFreshEntity(oFox);
//                    vanillaFox.remove(Entity.RemovalReason.DISCARDED);
//                }
//            } else {
                if (oFox != null) {
                    oFox.copyPosition(vanillaFox);

                    oFox.setCustomName(vanillaFox.getCustomName());
                    oFox.setAge(vanillaFox.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(3);
                    oFox.setVariant(randomVariant);

                    int randomOverlay = event.getLevel().getRandom().nextInt(OFoxMarkingLayer.Overlay.values().length);
                    oFox.setOverlayVariant(randomOverlay);

                    int randomGender = event.getLevel().getRandom().nextInt(OFox.Gender.values().length);
                    oFox.setGender(randomGender);

                    if (event.getLevel().isClientSide) {
                        vanillaFox.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(oFox);
                    vanillaFox.remove(Entity.RemovalReason.DISCARDED);
                }
            }


    }

}