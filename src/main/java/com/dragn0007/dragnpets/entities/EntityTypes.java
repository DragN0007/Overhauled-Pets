package com.dragn0007.dragnpets.entities;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.fox.OFox;
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

public class EntityTypes {

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
                            MobCategory.CREATURE)
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
                            MobCategory.CREATURE)
                    .sized(0.4f,0.4f)
                    .build(new ResourceLocation(LivestockOverhaul.MODID,"o_tropical_fish").toString()));

}

