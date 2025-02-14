package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlMarkingLayer;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlModel;
import com.dragn0007.dragnpets.entities.cat.*;
import com.dragn0007.dragnpets.entities.dog.*;
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
import net.minecraft.world.level.biome.Biomes;
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

            if (event.getEntity().getClass() == Wolf.class) {
                Wolf vanillaWolf = (Wolf) event.getEntity();

                if (event.getLevel().isClientSide) {
                    return;
                }

                OWolf oWolf = EntityTypes.O_WOLF_ENTITY.get().create(event.getLevel());
                if (oWolf != null) {
                    oWolf.copyPosition(vanillaWolf);
                    oWolf.setOwnerUUID(vanillaWolf.getOwnerUUID());

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
        }

        //Ocelot
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_OCELOTS.get() && event.getEntity() instanceof Ocelot) {

            if (event.getEntity().getClass() == Ocelot.class) {
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
        }

        //Fox
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_FOXES.get() && event.getEntity() instanceof Fox) {

            if (event.getEntity().getClass() == Fox.class) {
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
        }

        //Axolotl
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_AXOLOTLS.get() && event.getEntity() instanceof Axolotl) {

            if (event.getEntity().getClass() == Axolotl.class) {
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
        }

        //Parrot
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_PARROTS.get() && event.getEntity() instanceof Parrot) {

            if (event.getEntity().getClass() == Parrot.class) {
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
                        macaw.setOwnerUUID(vanillaParrot.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(MacawModel.Variant.values().length);
                        macaw.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(OParrot.Gender.values().length);
                        macaw.setGender(randomGender);

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
                        cockatiel.setOwnerUUID(vanillaParrot.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(CockatielModel.Variant.values().length);
                        cockatiel.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(OParrot.Gender.values().length);
                        cockatiel.setGender(randomGender);

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
                        ringneck.setOwnerUUID(vanillaParrot.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(RingneckModel.Variant.values().length);
                        ringneck.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(OParrot.Gender.values().length);
                        ringneck.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            vanillaParrot.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(ringneck);
                        vanillaParrot.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }
            }
        }

        //Tropical Fish
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_TROPICAL_FISH.get() && event.getEntity() instanceof TropicalFish) {

            if (event.getEntity().getClass() == TropicalFish.class) {
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

        //Cat (includes Dogs)
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_CATS.get() && event.getEntity() instanceof Cat) {

            if (event.getEntity().getClass() == Cat.class) {
                Cat cat = (Cat) event.getEntity();

                OCat commonCat = EntityTypes.O_CAT_ENTITY.get().create(event.getLevel());
                Doberman doberman = EntityTypes.DOBERMAN_ENTITY.get().create(event.getLevel());
                Labrador labrador = EntityTypes.LABRADOR_ENTITY.get().create(event.getLevel());
                Husky husky = EntityTypes.HUSKY_ENTITY.get().create(event.getLevel());
                Pyrenees pyrenees = EntityTypes.PYRENEES_ENTITY.get().create(event.getLevel());
                Collie collie = EntityTypes.BORDER_COLLIE_ENTITY.get().create(event.getLevel());
                MaineCoon maineCoon = EntityTypes.MAINE_COON_ENTITY.get().create(event.getLevel());
                Bernese bernese = EntityTypes.BERNESE_ENTITY.get().create(event.getLevel());

                if (event.getLevel().isClientSide) {
                    return;
                }

                int i = event.getLevel().getRandom().nextInt(9);

                if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                    if (commonCat != null) {
                        commonCat.copyPosition(cat);

                        commonCat.setCustomName(cat.getCustomName());
                        commonCat.setAge(cat.getAge());
                        commonCat.setOwnerUUID(cat.getOwnerUUID());

                        commonCat.setVariant(0);
                        commonCat.setOverlay(0);

                        int randomEyes = event.getLevel().getRandom().nextInt(OCatEyeLayer.Eyes.values().length);
                        commonCat.setEyes(randomEyes);

                        int randomGender = event.getLevel().getRandom().nextInt(OCat.Gender.values().length);
                        commonCat.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(commonCat);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (commonCat != null) {
                    if (i <= 1 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        commonCat.copyPosition(cat);

                        commonCat.setCustomName(cat.getCustomName());
                        commonCat.setAge(cat.getAge());
                        commonCat.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(OCatModel.Variant.values().length);
                        commonCat.setVariant(randomVariant);

                        int randomOverlay = event.getLevel().getRandom().nextInt(OCatMarkingLayer.Overlay.values().length);
                        commonCat.setOverlay(randomOverlay);

                        int randomEyes = event.getLevel().getRandom().nextInt(OCatEyeLayer.Eyes.values().length);
                        commonCat.setEyes(randomEyes);

                        int randomGender = event.getLevel().getRandom().nextInt(OCat.Gender.values().length);
                        commonCat.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(commonCat);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (doberman != null) {
                    if (i == 2 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        doberman.copyPosition(cat);

                        doberman.setCustomName(cat.getCustomName());
                        doberman.setAge(cat.getAge());
                        doberman.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(DobermanModel.Variant.values().length);
                        doberman.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        doberman.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(doberman);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (labrador != null) {
                    if (i == 3 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        labrador.copyPosition(cat);

                        labrador.setCustomName(cat.getCustomName());
                        labrador.setAge(cat.getAge());
                        labrador.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(LabradorModel.Variant.values().length);
                        labrador.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        labrador.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(labrador);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (husky != null) {
                    if (i == 4 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        husky.copyPosition(cat);

                        husky.setCustomName(cat.getCustomName());
                        husky.setAge(cat.getAge());
                        husky.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(HuskyModel.Variant.values().length);
                        husky.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        husky.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(husky);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (pyrenees != null) {
                    if (i == 5 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        pyrenees.copyPosition(cat);

                        pyrenees.setCustomName(cat.getCustomName());
                        pyrenees.setAge(cat.getAge());
                        pyrenees.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(PyreneesModel.Variant.values().length);
                        pyrenees.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        pyrenees.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(pyrenees);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (collie != null) {
                    if (i == 6 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        collie.copyPosition(cat);

                        collie.setCustomName(cat.getCustomName());
                        collie.setAge(cat.getAge());
                        collie.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(CollieModel.Variant.values().length);
                        collie.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        collie.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(collie);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (maineCoon != null) {
                    if (i == 7 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        maineCoon.copyPosition(cat);

                        maineCoon.setCustomName(cat.getCustomName());
                        maineCoon.setAge(cat.getAge());
                        maineCoon.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(MaineCoonModel.Variant.values().length);
                        maineCoon.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(OCat.Gender.values().length);
                        maineCoon.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(maineCoon);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }

                if (bernese != null) {
                    if (i == 8 && !event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                        bernese.copyPosition(cat);

                        bernese.setCustomName(cat.getCustomName());
                        bernese.setAge(cat.getAge());
                        bernese.setOwnerUUID(cat.getOwnerUUID());

                        int randomVariant = event.getLevel().getRandom().nextInt(BerneseModel.Variant.values().length);
                        bernese.setVariant(randomVariant);

                        int randomGender = event.getLevel().getRandom().nextInt(ODog.Gender.values().length);
                        bernese.setGender(randomGender);

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(bernese);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }
                }
            }
        }


    }

}