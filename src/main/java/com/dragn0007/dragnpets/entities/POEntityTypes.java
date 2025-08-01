package com.dragn0007.dragnpets.entities;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRex;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoon;
import com.dragn0007.dragnpets.entities.cat.manx.Manx;
import com.dragn0007.dragnpets.entities.dog.CommonDog;
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
import com.dragn0007.dragnpets.entities.misc.sled.DogSled;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.parrot.Cockatiel;
import com.dragn0007.dragnpets.entities.parrot.Macaw;
import com.dragn0007.dragnpets.entities.parrot.Ringneck;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dragn0007.dragnpets.PetsOverhaul.MODID;

public class POEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<OWolf>> O_WOLF_ENTITY = ENTITY_TYPES.register("o_wolf",
            () -> EntityType.Builder.of(OWolf::new,
                            MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_wolf").toString()));

    public static final RegistryObject<EntityType<OOcelot>> O_OCELOT_ENTITY = ENTITY_TYPES.register("o_ocelot",
            () -> EntityType.Builder.of(OOcelot::new,
                            MobCategory.CREATURE)
                    .sized(0.8f,0.8f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_ocelot").toString()));

    public static final RegistryObject<EntityType<OFox>> O_FOX_ENTITY = ENTITY_TYPES.register("o_fox",
            () -> EntityType.Builder.of(OFox::new,
                            MobCategory.CREATURE)
                    .sized(0.8f,0.8f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_fox").toString()));

    public static final RegistryObject<EntityType<OAxolotl>> O_AXOLOTL_ENTITY = ENTITY_TYPES.register("o_axolotl",
            () -> EntityType.Builder.of(OAxolotl::new,
                            MobCategory.AXOLOTLS)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_axolotl").toString()));

    public static final RegistryObject<EntityType<Macaw>> MACAW_ENTITY = ENTITY_TYPES.register("macaw",
            () -> EntityType.Builder.of(Macaw::new,
                            MobCategory.CREATURE)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"macaw").toString()));

    public static final RegistryObject<EntityType<Cockatiel>> COCKATIEL_ENTITY = ENTITY_TYPES.register("cockatiel",
            () -> EntityType.Builder.of(Cockatiel::new,
                            MobCategory.CREATURE)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"cockatiel").toString()));

    public static final RegistryObject<EntityType<Ringneck>> RINGNECK_ENTITY = ENTITY_TYPES.register("ringneck",
            () -> EntityType.Builder.of(Ringneck::new,
                            MobCategory.CREATURE)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"ringneck").toString()));

    public static final RegistryObject<EntityType<OTropicalFish>> O_TROPICAL_FISH_ENTITY = ENTITY_TYPES.register("o_tropical_fish",
            () -> EntityType.Builder.of(OTropicalFish::new,
                            MobCategory.WATER_AMBIENT)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_tropical_fish").toString()));

    public static final RegistryObject<EntityType<CommonDog>> O_DOG_ENTITY = ENTITY_TYPES.register("o_dog",
            () -> EntityType.Builder.of(CommonDog::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_dog").toString()));

    public static final RegistryObject<EntityType<Doberman>> DOBERMAN_ENTITY = ENTITY_TYPES.register("doberman",
            () -> EntityType.Builder.of(Doberman::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"doberman").toString()));

    public static final RegistryObject<EntityType<OCat>> O_CAT_ENTITY = ENTITY_TYPES.register("o_cat",
            () -> EntityType.Builder.of(OCat::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_cat").toString()));

    public static final RegistryObject<EntityType<Labrador>> LABRADOR_ENTITY = ENTITY_TYPES.register("labrador",
            () -> EntityType.Builder.of(Labrador::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"labrador").toString()));

    public static final RegistryObject<EntityType<Husky>> HUSKY_ENTITY = ENTITY_TYPES.register("husky",
            () -> EntityType.Builder.of(Husky::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"husky").toString()));

    public static final RegistryObject<EntityType<Pyrenees>> PYRENEES_ENTITY = ENTITY_TYPES.register("pyrenees",
            () -> EntityType.Builder.of(Pyrenees::new,
                            MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"pyrenees").toString()));

    public static final RegistryObject<EntityType<Collie>> BORDER_COLLIE_ENTITY = ENTITY_TYPES.register("border_collie",
            () -> EntityType.Builder.of(Collie::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"border_collie").toString()));

    public static final RegistryObject<EntityType<Bernese>> BERNESE_ENTITY = ENTITY_TYPES.register("bernese",
            () -> EntityType.Builder.of(Bernese::new,
                            MobCategory.CREATURE)
                    .sized(0.9f,0.9f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"bernese").toString()));

    public static final RegistryObject<EntityType<MaineCoon>> MAINE_COON_ENTITY = ENTITY_TYPES.register("maine_coon",
            () -> EntityType.Builder.of(MaineCoon::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"maine_coon").toString()));

    public static final RegistryObject<EntityType<AustralianShepherd>> AUSTRALIAN_SHEPHERD_ENTITY = ENTITY_TYPES.register("australian_shepherd",
            () -> EntityType.Builder.of(AustralianShepherd::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"australian_shepherd").toString()));

    public static final RegistryObject<EntityType<Bloodhound>> BLOODHOUND_ENTITY = ENTITY_TYPES.register("bloodhound",
            () -> EntityType.Builder.of(Bloodhound::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"bloodhound").toString()));

    public static final RegistryObject<EntityType<KornishRex>> KORNISH_REX_ENTITY = ENTITY_TYPES.register("kornish_rex",
            () -> EntityType.Builder.of(KornishRex::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"kornish_rex").toString()));

    public static final RegistryObject<EntityType<CockerSpaniel>> COCKER_SPANIEL_ENTITY = ENTITY_TYPES.register("cocker_spaniel",
            () -> EntityType.Builder.of(CockerSpaniel::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"cocker_spaniel").toString()));

    public static final RegistryObject<EntityType<Whippet>> WHIPPET_ENTITY = ENTITY_TYPES.register("whippet",
            () -> EntityType.Builder.of(Whippet::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"whippet").toString()));

    public static final RegistryObject<EntityType<Rottweiler>> ROTTWEILER_ENTITY = ENTITY_TYPES.register("rottweiler",
            () -> EntityType.Builder.of(Rottweiler::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"rottweiler").toString()));

    public static final RegistryObject<EntityType<Manx>> MANX_ENTITY = ENTITY_TYPES.register("manx",
            () -> EntityType.Builder.of(Manx::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"manx").toString()));

    public static final RegistryObject<EntityType<AmericanRidgeback>> AMERICAN_RIDGEBACK_ENTITY = ENTITY_TYPES.register("american_ridgeback",
            () -> EntityType.Builder.of(AmericanRidgeback::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"american_ridgeback").toString()));

    public static final RegistryObject<EntityType<Beagle>> BEAGLE_ENTITY = ENTITY_TYPES.register("beagle",
            () -> EntityType.Builder.of(Beagle::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"beagle").toString()));

    public static final RegistryObject<EntityType<Coonhound>> COONHOUND_ENTITY = ENTITY_TYPES.register("coonhound",
            () -> EntityType.Builder.of(Coonhound::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"coonhound").toString()));

    public static final RegistryObject<EntityType<Foxhound>> FOXHOUND_ENTITY = ENTITY_TYPES.register("foxhound",
            () -> EntityType.Builder.of(Foxhound::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"foxhound").toString()));

    public static final RegistryObject<EntityType<JackRussell>> JACK_RUSSELL_ENTITY = ENTITY_TYPES.register("jack_russell",
            () -> EntityType.Builder.of(JackRussell::new,
                            MobCategory.CREATURE)
                    .sized(0.6f,0.6f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"jack_russell").toString()));


    public static final RegistryObject<EntityType<DogSled>> DOG_SLED_ENTITY = ENTITY_TYPES.register("dog_sled",
            () -> EntityType.Builder.of(DogSled::new,
                            MobCategory.MISC)
                    .sized(1.2f,0.3f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"dog_sled").toString()));

}

