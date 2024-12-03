package com.dragn0007.dragnpets.entities;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
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

}

