package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlMarkingLayer;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlModel;
import com.dragn0007.dragnpets.entities.cat.CatMarkingOverlay;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.OCatEyeLayer;
import com.dragn0007.dragnpets.entities.cat.OCatModel;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRex;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoon;
import com.dragn0007.dragnpets.entities.cat.manx.Manx;
import com.dragn0007.dragnpets.entities.dog.CommonDog;
import com.dragn0007.dragnpets.entities.dog.CommonDogModel;
import com.dragn0007.dragnpets.entities.dog.DogMarkingOverlay;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.dog.american_ridgeback.AmericanRidgeback;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherd;
import com.dragn0007.dragnpets.entities.dog.beagle.Beagle;
import com.dragn0007.dragnpets.entities.dog.bernese.Bernese;
import com.dragn0007.dragnpets.entities.dog.bloodhound.Bloodhound;
import com.dragn0007.dragnpets.entities.dog.border_collie.Collie;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpaniel;
import com.dragn0007.dragnpets.entities.dog.coonhound.Coonhound;
import com.dragn0007.dragnpets.entities.dog.doberman.Doberman;
import com.dragn0007.dragnpets.entities.dog.foxhound.Foxhound;
import com.dragn0007.dragnpets.entities.dog.husky.Husky;
import com.dragn0007.dragnpets.entities.dog.jack_russell.JackRussell;
import com.dragn0007.dragnpets.entities.dog.labrador.Labrador;
import com.dragn0007.dragnpets.entities.dog.pyrenees.Pyrenees;
import com.dragn0007.dragnpets.entities.dog.rottweiler.Rottweiler;
import com.dragn0007.dragnpets.entities.dog.whippet.Whippet;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxMarkingLayer;
import com.dragn0007.dragnpets.entities.fox.OFoxModel;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotEyeLayer;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotMarkingLayer;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotModel;
import com.dragn0007.dragnpets.entities.parrot.*;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishMarkingLayer;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishModel;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.entities.wolf.OWolfMarkingLayer;
import com.dragn0007.dragnpets.entities.wolf.OWolfModel;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {

    // This class falls under the LGPL license, as stated in the CODE_LICENSE.txt
    // Some of this code was referenced from Realistic Horse Genetics. Please check them out, too! :)
    // https://github.com/sekelsta/horse-colors  |  https://www.curseforge.com/minecraft/mc-mods/realistic-horse-genetics

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {

        Random random = new Random();

        //Wolf

        OWolf oWolf = POEntityTypes.O_WOLF_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_WOLVES.get() && event.getEntity() instanceof Wolf vanillaWolf) {

            if (event.getEntity().getClass() == Wolf.class && (((!(vanillaWolf.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oWolf != null) {
                    oWolf.copyPosition(vanillaWolf);
                    oWolf.setOwnerUUID(vanillaWolf.getOwnerUUID());

                    oWolf.setCustomName(vanillaWolf.getCustomName());
                    oWolf.setAge(vanillaWolf.getAge());

                    oWolf.setGender(random.nextInt(OWolf.Gender.values().length));
                    oWolf.setOverlayVariant(random.nextInt(OWolfMarkingLayer.Overlay.values().length));

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_HOT_OVERWORLD)) {
                            int[] variants = {3, 5, 6};
                            int randomIndex = new Random().nextInt(variants.length);
                            oWolf.setVariant(variants[randomIndex]);
                        } else if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            int[] variants = {4, 7, 8};
                            int randomIndex = new Random().nextInt(variants.length);
                            oWolf.setVariant(variants[randomIndex]);
                        } else if (random.nextDouble() > 0.30) {
                            int[] variants = {0, 1, 2, 7};
                            int randomIndex = new Random().nextInt(variants.length);
                            oWolf.setVariant(variants[randomIndex]);
                        }
                    } else {
                        oWolf.setVariant(random.nextInt(OWolfModel.Variant.values().length));
                    }

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
        OOcelot oOcelot = POEntityTypes.O_OCELOT_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_OCELOTS.get() && event.getEntity() instanceof Ocelot vanillaOcelot) {

            if (event.getEntity().getClass() == Ocelot.class && (((!(vanillaOcelot.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oOcelot != null) {
                    oOcelot.copyPosition(vanillaOcelot);

                    oOcelot.setCustomName(vanillaOcelot.getCustomName());
                    oOcelot.setAge(vanillaOcelot.getAge());

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        oOcelot.setColor();
                        oOcelot.setMarking();
                        oOcelot.setEyeColor();
                    } else {
                        oOcelot.setVariant(random.nextInt(OOcelotModel.Variant.values().length));
                        oOcelot.setOverlayVariant(random.nextInt(OOcelotMarkingLayer.Overlay.values().length));
                        oOcelot.setEyes(random.nextInt(OOcelotEyeLayer.Eyes.values().length));
                    }

                    oOcelot.setGender(random.nextInt(OOcelot.Gender.values().length));

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
        OFox oFox = POEntityTypes.O_FOX_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_FOXES.get() && event.getEntity() instanceof Fox vanillaFox) {

            if (event.getEntity().getClass() == Fox.class && (((!(vanillaFox.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                if (oFox != null) {
                    oFox.copyPosition(vanillaFox);

                    oFox.setCustomName(vanillaFox.getCustomName());
                    oFox.setAge(vanillaFox.getAge());

                    if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                        if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Tags.Biomes.IS_COLD_OVERWORLD)) {
                            if (random.nextDouble() < 0.05) {
                                int[] variants = {5, 7, 8, 12};
                                int randomIndex = new Random().nextInt(variants.length);
                                oFox.setVariant(variants[randomIndex]);
                            } else {
                                oFox.setVariant(12);
                            }
                        } else {
                            if (random.nextDouble() < 0.05) {
                                oFox.setVariant(12);
                            } else {
                                int[] variants = {5, 7, 8, 12};
                                int randomIndex = new Random().nextInt(variants.length);
                                oFox.setVariant(variants[randomIndex]);
                            }
                        }
                        oFox.setMarking();
                    } else {
                        oFox.setVariant(random.nextInt(OFoxModel.Variant.values().length));
                        oFox.setOverlayVariant(random.nextInt(OFoxMarkingLayer.Overlay.values().length));
                    }

                    oFox.setGender(random.nextInt(OFox.Gender.values().length));

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
        OAxolotl oAxolotl = POEntityTypes.O_AXOLOTL_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_AXOLOTLS.get() && event.getEntity() instanceof Axolotl vanillaAxolotl) {

            if (event.getEntity().getClass() == Axolotl.class && (((!(vanillaAxolotl.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

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
        Macaw macaw = POEntityTypes.MACAW_ENTITY.get().create(event.getLevel());
        Cockatiel cockatiel = POEntityTypes.COCKATIEL_ENTITY.get().create(event.getLevel());
        Ringneck ringneck = POEntityTypes.RINGNECK_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_PARROTS.get() && event.getEntity() instanceof Parrot vanillaParrot) {

            if (event.getEntity().getClass() == Parrot.class && (((!(vanillaParrot.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

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
        OTropicalFish oTropicalFish = POEntityTypes.O_TROPICAL_FISH_ENTITY.get().create(event.getLevel());
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_TROPICAL_FISH.get() && event.getEntity() instanceof TropicalFish vanillaTropicalFish) {

            if (event.getEntity().getClass() == TropicalFish.class && (((!(vanillaTropicalFish.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

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
        OCat commonCat = POEntityTypes.O_CAT_ENTITY.get().create(event.getLevel());
        CommonDog commonDog = POEntityTypes.O_DOG_ENTITY.get().create(event.getLevel());
        Doberman doberman = POEntityTypes.DOBERMAN_ENTITY.get().create(event.getLevel());
        Labrador labrador = POEntityTypes.LABRADOR_ENTITY.get().create(event.getLevel());
        Husky husky = POEntityTypes.HUSKY_ENTITY.get().create(event.getLevel());
        Pyrenees pyrenees = POEntityTypes.PYRENEES_ENTITY.get().create(event.getLevel());
        Collie collie = POEntityTypes.BORDER_COLLIE_ENTITY.get().create(event.getLevel());
        MaineCoon maineCoon = POEntityTypes.MAINE_COON_ENTITY.get().create(event.getLevel());
        Bernese bernese = POEntityTypes.BERNESE_ENTITY.get().create(event.getLevel());
        AustralianShepherd aShepherd = POEntityTypes.AUSTRALIAN_SHEPHERD_ENTITY.get().create(event.getLevel());
        Bloodhound bloodhound = POEntityTypes.BLOODHOUND_ENTITY.get().create(event.getLevel());
        KornishRex kornishRex = POEntityTypes.KORNISH_REX_ENTITY.get().create(event.getLevel());
        CockerSpaniel cockerSpaniel = POEntityTypes.COCKER_SPANIEL_ENTITY.get().create(event.getLevel());
        Whippet whippet = POEntityTypes.WHIPPET_ENTITY.get().create(event.getLevel());
        Rottweiler rottweiler = POEntityTypes.ROTTWEILER_ENTITY.get().create(event.getLevel());
        Manx manx = POEntityTypes.MANX_ENTITY.get().create(event.getLevel());
        AmericanRidgeback americanRidgeback = POEntityTypes.AMERICAN_RIDGEBACK_ENTITY.get().create(event.getLevel());
        Beagle beagle = POEntityTypes.BEAGLE_ENTITY.get().create(event.getLevel());
        Coonhound coonhound = POEntityTypes.COONHOUND_ENTITY.get().create(event.getLevel());
        Foxhound foxhound = POEntityTypes.FOXHOUND_ENTITY.get().create(event.getLevel());
        JackRussell jackRussell = POEntityTypes.JACK_RUSSELL_ENTITY.get().create(event.getLevel());

        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_CATS.get() && event.getEntity() instanceof Cat cat) {

            if (event.getEntity().getClass() == Cat.class && (((!(cat.getSpawnType() == MobSpawnType.SPAWN_EGG)) && !LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get()) || LivestockOverhaulCommonConfig.REPLACE_SPAWN_EGG_ANIMALS.get())) {

                if (event.getLevel().isClientSide) {
                    return;
                }

                int i = event.getLevel().getRandom().nextInt(59);

                if (event.getLevel().getBiome(event.getEntity().blockPosition()).is(Biomes.SWAMP)) {
                    if (commonCat != null) {
                        commonCat.copyPosition(cat);

                        commonCat.setCustomName(cat.getCustomName());
                        commonCat.setAge(cat.getAge());
                        commonCat.setOwnerUUID(cat.getOwnerUUID());

                        if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                            commonCat.setEyeColor();
                        } else {
                            commonCat.setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
                        }

                        commonCat.setVariant(0);
                        commonCat.setOverlayVariant(0);
                        commonCat.setGender(random.nextInt(OCat.Gender.values().length));

                        if (event.getLevel().isClientSide) {
                            cat.remove(Entity.RemovalReason.DISCARDED);
                        }

                        event.getLevel().addFreshEntity(commonCat);
                        cat.remove(Entity.RemovalReason.DISCARDED);

                        event.setCanceled(true);
                    }

                } else {
                    if (commonCat != null) {
                        if (i <= 30) {
                            commonCat.copyPosition(cat);

                            commonCat.setCustomName(cat.getCustomName());
                            commonCat.setAge(cat.getAge());
                            commonCat.setOwnerUUID(cat.getOwnerUUID());

                            commonCat.setGender(random.nextInt(OCat.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                commonCat.setEyeColor();
                            } else {
                                commonCat.setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
                            }

                            commonCat.setVariant(random.nextInt(OCatModel.Variant.values().length));
                            commonCat.setOverlayVariant(random.nextInt(CatMarkingOverlay.values().length));

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(commonCat);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (commonDog != null) {
                        if (i > 30 && i < 40) {
                            commonDog.copyPosition(cat);

                            commonDog.setCustomName(cat.getCustomName());
                            commonDog.setAge(cat.getAge());
                            commonDog.setOwnerUUID(cat.getOwnerUUID());

                            commonDog.setGender(random.nextInt(ODog.Gender.values().length));
                            commonDog.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                            commonDog.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            commonDog.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                commonDog.setCropChance();
                            } else {
                                commonDog.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(commonDog);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (bloodhound != null) {
                        if (i == 41) {
                            bloodhound.copyPosition(cat);

                            bloodhound.setCustomName(cat.getCustomName());
                            bloodhound.setAge(cat.getAge());
                            bloodhound.setOwnerUUID(cat.getOwnerUUID());

                            bloodhound.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                bloodhound.setColor();
                                bloodhound.setMarking();
                            } else {
                                bloodhound.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                bloodhound.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            bloodhound.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                bloodhound.setCropChance();
                            } else {
                                bloodhound.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(bloodhound);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (doberman != null) {
                        if (i == 42) {
                            doberman.copyPosition(cat);

                            doberman.setCustomName(cat.getCustomName());
                            doberman.setAge(cat.getAge());
                            doberman.setOwnerUUID(cat.getOwnerUUID());

                            doberman.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                doberman.setColor();
                                doberman.setMarking();
                            } else {
                                doberman.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                doberman.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            doberman.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                doberman.setCropChance();
                            } else {
                                doberman.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(doberman);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (bernese != null) {
                        if (i == 43) {
                            bernese.copyPosition(cat);

                            bernese.setCustomName(cat.getCustomName());
                            bernese.setAge(cat.getAge());
                            bernese.setOwnerUUID(cat.getOwnerUUID());

                            bernese.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                bernese.setColor();
                                bernese.setMarking();
                            } else {
                                bernese.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                bernese.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            bernese.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                bernese.setCropChance();
                            } else {
                                bernese.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(bernese);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (aShepherd != null) {
                        if (i == 44) {
                            aShepherd.copyPosition(cat);

                            aShepherd.setCustomName(cat.getCustomName());
                            aShepherd.setAge(cat.getAge());
                            aShepherd.setOwnerUUID(cat.getOwnerUUID());

                            aShepherd.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                aShepherd.setColor();
                                aShepherd.setMarking();
                            } else {
                                aShepherd.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                aShepherd.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            aShepherd.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                aShepherd.setCropChance();
                            } else {
                                aShepherd.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(aShepherd);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (collie != null) {
                        if (i == 45) {
                            collie.copyPosition(cat);

                            collie.setCustomName(cat.getCustomName());
                            collie.setAge(cat.getAge());
                            collie.setOwnerUUID(cat.getOwnerUUID());

                            collie.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                collie.setColor();
                                collie.setMarking();
                            } else {
                                collie.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                collie.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            collie.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                collie.setCropChance();
                            } else {
                                collie.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(collie);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (cockerSpaniel != null) {
                        if (i == 46) {
                            cockerSpaniel.copyPosition(cat);

                            cockerSpaniel.setCustomName(cat.getCustomName());
                            cockerSpaniel.setAge(cat.getAge());
                            cockerSpaniel.setOwnerUUID(cat.getOwnerUUID());

                            cockerSpaniel.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                cockerSpaniel.setColor();
                                cockerSpaniel.setMarking();
                            } else {
                                cockerSpaniel.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                cockerSpaniel.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            cockerSpaniel.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                cockerSpaniel.setCropChance();
                            } else {
                                cockerSpaniel.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(cockerSpaniel);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (husky != null) {
                        if (i == 47) {
                            husky.copyPosition(cat);

                            husky.setCustomName(cat.getCustomName());
                            husky.setAge(cat.getAge());
                            husky.setOwnerUUID(cat.getOwnerUUID());

                            husky.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                husky.setColor();
                                husky.setMarking();
                            } else {
                                husky.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                husky.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            husky.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                husky.setCropChance();
                            } else {
                                husky.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(husky);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (pyrenees != null) {
                        if (i == 47) {
                            pyrenees.copyPosition(cat);

                            pyrenees.setCustomName(cat.getCustomName());
                            pyrenees.setAge(cat.getAge());
                            pyrenees.setOwnerUUID(cat.getOwnerUUID());

                            pyrenees.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                pyrenees.setColor();
                                pyrenees.setMarking();
                            } else {
                                pyrenees.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                pyrenees.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            pyrenees.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                pyrenees.setCropChance();
                            } else {
                                pyrenees.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(pyrenees);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (kornishRex != null) {
                        if (i == 48) {
                            kornishRex.copyPosition(cat);

                            kornishRex.setCustomName(cat.getCustomName());
                            kornishRex.setAge(cat.getAge());
                            kornishRex.setOwnerUUID(cat.getOwnerUUID());

                            kornishRex.setGender(random.nextInt(OCat.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                kornishRex.setEyeColor();
                            } else {
                                kornishRex.setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
                            }

                            kornishRex.setVariant(random.nextInt(OCatModel.Variant.values().length));
                            kornishRex.setOverlayVariant(random.nextInt(CatMarkingOverlay.values().length));

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(kornishRex);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (maineCoon != null) {
                        if (i == 49) {
                            maineCoon.copyPosition(cat);

                            maineCoon.setCustomName(cat.getCustomName());
                            maineCoon.setAge(cat.getAge());
                            maineCoon.setOwnerUUID(cat.getOwnerUUID());

                            maineCoon.setGender(random.nextInt(OCat.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                maineCoon.setEyeColor();
                            } else {
                                maineCoon.setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
                            }

                            maineCoon.setVariant(random.nextInt(OCatModel.Variant.values().length));
                            maineCoon.setOverlayVariant(random.nextInt(CatMarkingOverlay.values().length));

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(maineCoon);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (manx != null) {
                        if (i == 49) {
                            manx.copyPosition(cat);

                            manx.setCustomName(cat.getCustomName());
                            manx.setAge(cat.getAge());
                            manx.setOwnerUUID(cat.getOwnerUUID());

                            manx.setGender(random.nextInt(OCat.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                manx.setEyeColor();
                            } else {
                                manx.setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
                            }

                            manx.setVariant(random.nextInt(OCatModel.Variant.values().length));
                            manx.setOverlayVariant(random.nextInt(CatMarkingOverlay.values().length));

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(manx);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (americanRidgeback != null) {
                        if (i == 51) {
                            if (ModList.get().isLoaded("deadlydinos")) {
                                americanRidgeback.copyPosition(cat);

                                americanRidgeback.setCustomName(cat.getCustomName());
                                americanRidgeback.setAge(cat.getAge());
                                americanRidgeback.setOwnerUUID(cat.getOwnerUUID());

                                americanRidgeback.setGender(random.nextInt(ODog.Gender.values().length));

                                if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                    americanRidgeback.setColor();
                                    americanRidgeback.setMarking();
                                } else {
                                    americanRidgeback.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                    americanRidgeback.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                                }

                                americanRidgeback.setFluffChance();

                                if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                    americanRidgeback.setCropChance();
                                } else {
                                    americanRidgeback.setCropped(0);
                                }

                                if (event.getLevel().isClientSide) {
                                    cat.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(americanRidgeback);
                                cat.remove(Entity.RemovalReason.DISCARDED);

                                event.setCanceled(true);
                            } else {
                                commonDog.copyPosition(cat);

                                commonDog.setCustomName(cat.getCustomName());
                                commonDog.setAge(cat.getAge());
                                commonDog.setOwnerUUID(cat.getOwnerUUID());

                                commonDog.setGender(random.nextInt(ODog.Gender.values().length));
                                commonDog.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                commonDog.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                                commonDog.setFluffChance();

                                if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                    commonDog.setCropChance();
                                } else {
                                    commonDog.setCropped(0);
                                }

                                if (event.getLevel().isClientSide) {
                                    cat.remove(Entity.RemovalReason.DISCARDED);
                                }

                                event.getLevel().addFreshEntity(commonDog);
                                cat.remove(Entity.RemovalReason.DISCARDED);

                                event.setCanceled(true);
                            }
                        }
                    }

                    if (labrador != null) {
                        if (i == 52) {
                            labrador.copyPosition(cat);

                            labrador.setCustomName(cat.getCustomName());
                            labrador.setAge(cat.getAge());
                            labrador.setOwnerUUID(cat.getOwnerUUID());

                            labrador.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                labrador.setColor();
                                labrador.setMarking();
                            } else {
                                labrador.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                labrador.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            labrador.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                labrador.setCropChance();
                            } else {
                                labrador.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(labrador);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (rottweiler != null) {
                        if (i == 53) {
                            rottweiler.copyPosition(cat);

                            rottweiler.setCustomName(cat.getCustomName());
                            rottweiler.setAge(cat.getAge());
                            rottweiler.setOwnerUUID(cat.getOwnerUUID());

                            rottweiler.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                rottweiler.setColor();
                                rottweiler.setMarking();
                            } else {
                                rottweiler.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                rottweiler.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            rottweiler.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                rottweiler.setCropChance();
                            } else {
                                rottweiler.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(rottweiler);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (whippet != null) {
                        if (i == 54) {
                            whippet.copyPosition(cat);

                            whippet.setCustomName(cat.getCustomName());
                            whippet.setAge(cat.getAge());
                            whippet.setOwnerUUID(cat.getOwnerUUID());

                            whippet.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                whippet.setColor();
                                whippet.setMarking();
                            } else {
                                whippet.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                whippet.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            whippet.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                whippet.setCropChance();
                            } else {
                                whippet.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(whippet);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (beagle != null) {
                        if (i == 55) {
                            beagle.copyPosition(cat);

                            beagle.setCustomName(cat.getCustomName());
                            beagle.setAge(cat.getAge());
                            beagle.setOwnerUUID(cat.getOwnerUUID());

                            beagle.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                beagle.setColor();
                                beagle.setMarking();
                            } else {
                                beagle.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                beagle.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            beagle.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                beagle.setCropChance();
                            } else {
                                beagle.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(beagle);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (coonhound != null) {
                        if (i == 56) {
                            coonhound.copyPosition(cat);

                            coonhound.setCustomName(cat.getCustomName());
                            coonhound.setAge(cat.getAge());
                            coonhound.setOwnerUUID(cat.getOwnerUUID());

                            coonhound.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                coonhound.setColor();
                                coonhound.setMarking();
                            } else {
                                coonhound.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                coonhound.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            coonhound.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                coonhound.setCropChance();
                            } else {
                                coonhound.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(coonhound);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (jackRussell != null) {
                        if (i == 57) {
                            jackRussell.copyPosition(cat);

                            jackRussell.setCustomName(cat.getCustomName());
                            jackRussell.setAge(cat.getAge());
                            jackRussell.setOwnerUUID(cat.getOwnerUUID());

                            jackRussell.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                jackRussell.setColor();
                                jackRussell.setMarking();
                            } else {
                                jackRussell.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                jackRussell.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            jackRussell.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                jackRussell.setCropChance();
                            } else {
                                jackRussell.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(jackRussell);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }


                    if (jackRussell != null) {
                        if (i == 57) {
                            jackRussell.copyPosition(cat);

                            jackRussell.setCustomName(cat.getCustomName());
                            jackRussell.setAge(cat.getAge());
                            jackRussell.setOwnerUUID(cat.getOwnerUUID());

                            jackRussell.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                jackRussell.setColor();
                                jackRussell.setMarking();
                            } else {
                                jackRussell.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                jackRussell.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            jackRussell.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                jackRussell.setCropChance();
                            } else {
                                jackRussell.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(jackRussell);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }

                    if (foxhound != null) {
                        if (i == 58) {
                            foxhound.copyPosition(cat);

                            foxhound.setCustomName(cat.getCustomName());
                            foxhound.setAge(cat.getAge());
                            foxhound.setOwnerUUID(cat.getOwnerUUID());

                            foxhound.setGender(random.nextInt(ODog.Gender.values().length));

                            if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
                                foxhound.setColor();
                                foxhound.setMarking();
                            } else {
                                foxhound.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
                                foxhound.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
                            }

                            foxhound.setFluffChance();

                            if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
                                foxhound.setCropChance();
                            } else {
                                foxhound.setCropped(0);
                            }

                            if (event.getLevel().isClientSide) {
                                cat.remove(Entity.RemovalReason.DISCARDED);
                            }

                            event.getLevel().addFreshEntity(foxhound);
                            cat.remove(Entity.RemovalReason.DISCARDED);

                            event.setCanceled(true);
                        }
                    }


                }
            }
        }


    }

}