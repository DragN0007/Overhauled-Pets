package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlMarkingLayer;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlModel;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxMarkingLayer;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotModel;
import com.dragn0007.dragnpets.entities.parrot.*;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishMarkingLayer;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishModel;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.entities.wolf.OWolfModel;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
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

                event.setCanceled(true);
            }
        }

        //Axolotl
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_AXOLOTLS.get() && event.getEntity() instanceof Axolotl) {
            Axolotl vanillaAxolotl = (Axolotl) event.getEntity();
            OAxolotl oAxolotl = EntityTypes.O_AXOLOTL_ENTITY.get().create(event.getLevel());

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oAxolotl != null) {
                oAxolotl.copyPosition(vanillaAxolotl);

                oAxolotl.setCustomName(vanillaAxolotl.getCustomName());
                oAxolotl.setAge(vanillaAxolotl.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(OAxolotlModel.Variant.values().length);
                oAxolotl.setVariant(randomVariant);

                int randomOverlay = event.getLevel().getRandom().nextInt(OAxolotlMarkingLayer.Overlay.values().length);
                oAxolotl.setOverlayVariant(randomOverlay);

                int randomGills = event.getLevel().getRandom().nextInt(OAxolotl.Gills.values().length);
                oAxolotl.setGills(randomGills);

                if (event.getLevel().isClientSide) {
                    vanillaAxolotl.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oAxolotl);
                vanillaAxolotl.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }

        //Parrot
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_PARROTS.get() && event.getEntity() instanceof Parrot) {
            Parrot vanillaParrot = (Parrot) event.getEntity();
            Macaw macaw = EntityTypes.MACAW_ENTITY.get().create(event.getLevel());
            Cockatiel cockatiel = EntityTypes.COCKATIEL_ENTITY.get().create(event.getLevel());
            Ringneck ringneck = EntityTypes.RINGNECK_ENTITY.get().create(event.getLevel());

            if (event.getLevel().isClientSide) {
                return;
            }

            int i = event.getLevel().getRandom().nextInt(3);

            if (macaw != null) {
                if (i == 0) {
                    macaw.copyPosition(vanillaParrot);

                    macaw.setCustomName(vanillaParrot.getCustomName());
                    macaw.setAge(vanillaParrot.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(MacawModel.Variant.values().length);
                    macaw.setVariant(randomVariant);

                    if (event.getLevel().isClientSide) {
                        vanillaParrot.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(macaw);
                    vanillaParrot.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }

            if (cockatiel != null) {
                if (i == 1) {
                    cockatiel.copyPosition(vanillaParrot);

                    cockatiel.setCustomName(vanillaParrot.getCustomName());
                    cockatiel.setAge(vanillaParrot.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(CockatielModel.Variant.values().length);
                    cockatiel.setVariant(randomVariant);

                    if (event.getLevel().isClientSide) {
                        vanillaParrot.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(cockatiel);
                    vanillaParrot.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }

            if (ringneck != null) {
                if (i == 2) {
                    ringneck.copyPosition(vanillaParrot);

                    ringneck.setCustomName(vanillaParrot.getCustomName());
                    ringneck.setAge(vanillaParrot.getAge());

                    int randomVariant = event.getLevel().getRandom().nextInt(RingneckModel.Variant.values().length);
                    ringneck.setVariant(randomVariant);

                    if (event.getLevel().isClientSide) {
                        vanillaParrot.remove(Entity.RemovalReason.DISCARDED);
                    }

                    event.getLevel().addFreshEntity(ringneck);
                    vanillaParrot.remove(Entity.RemovalReason.DISCARDED);

                    event.setCanceled(true);
                }
            }
        }

        //Tropical Fish
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_TROPICAL_FISH.get() && event.getEntity() instanceof TropicalFish) {
            TropicalFish vanillaTropicalFish = (TropicalFish) event.getEntity();
            OTropicalFish oTropicalFish = EntityTypes.O_TROPICAL_FISH_ENTITY.get().create(event.getLevel());

            if (event.getLevel().isClientSide) {
                return;
            }

            if (oTropicalFish != null) {
                oTropicalFish.copyPosition(vanillaTropicalFish);

                oTropicalFish.setCustomName(vanillaTropicalFish.getCustomName());

                int randomVariant = event.getLevel().getRandom().nextInt(OTropicalFishModel.Variant.values().length);
                oTropicalFish.setVariant(randomVariant);

                int randomOverlay = event.getLevel().getRandom().nextInt(OTropicalFishMarkingLayer.Overlay.values().length);
                oTropicalFish.setOverlay(randomOverlay);

                int randomSpecies = event.getLevel().getRandom().nextInt(OTropicalFish.Species.values().length);
                oTropicalFish.setSpecies(randomSpecies);

                if (event.getLevel().isClientSide) {
                    vanillaTropicalFish.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oTropicalFish);
                vanillaTropicalFish.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }


    }

}