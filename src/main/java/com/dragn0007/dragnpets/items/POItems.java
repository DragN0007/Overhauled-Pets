package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.items.custom.DogSledItem;
import com.dragn0007.dragnpets.items.custom.TropicalFishRoeItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class POItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PetsOverhaul.MODID);

    //Spawn Eggs
    public static final RegistryObject<Item> O_WOLF_SPAWN_EGG = ITEMS.register("o_wolf_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_WOLF_ENTITY, 0xbbbbbb, 0x6d6d6d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_OCELOT_SPAWN_EGG = ITEMS.register("o_ocelot_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_OCELOT_ENTITY, 0xf6d587, 0x5e381b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_FOX_SPAWN_EGG = ITEMS.register("o_fox_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_FOX_ENTITY, 0xd5701c, 0x62483c, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_AXOLOTL_SPAWN_EGG = ITEMS.register("o_axolotl_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_AXOLOTL_ENTITY, 0xeed0cb, 0xc7a49e, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_TROPICAL_FISH_SPAWN_EGG = ITEMS.register("o_tropical_fish_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_TROPICAL_FISH_ENTITY, 0xce7b22, 0xffffff, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CAT_SPAWN_EGG = ITEMS.register("o_cat_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_CAT_ENTITY, 0xd5913f, 0xa7631a, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MACAW_SPAWN_EGG = ITEMS.register("macaw_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.MACAW_ENTITY, 0xab2f23, 0xda9e35, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> COCKATIEL_SPAWN_EGG = ITEMS.register("cockatiel_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.COCKATIEL_ENTITY, 0x9d9d9d, 0xfff469, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RINGNECK_SPAWN_EGG = ITEMS.register("ringneck_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.RINGNECK_ENTITY, 0xa3a855, 0x2f3515, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DOBERMAN_SPAWN_EGG = ITEMS.register("doberman_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.DOBERMAN_ENTITY, 0x232323, 0x8c4716, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LABRADOR_SPAWN_EGG = ITEMS.register("labrador_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.LABRADOR_ENTITY, 0xf2ca8c, 0xc8a56d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HUSKY_SPAWN_EGG = ITEMS.register("husky_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.HUSKY_ENTITY, 0x4a4743, 0xfaf4e9, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PYRENEES_SPAWN_EGG = ITEMS.register("pyrenees_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.PYRENEES_ENTITY, 0xfdfcf8, 0xe0dcd1, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BORDER_COLLIE_SPAWN_EGG = ITEMS.register("border_collie_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.BORDER_COLLIE_ENTITY, 0x683f20, 0xfff8f8, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MAINE_COON_SPAWN_EGG = ITEMS.register("maine_coon_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.MAINE_COON_ENTITY, 0x565353, 0x413f3f, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BERNESE_SPAWN_EGG = ITEMS.register("bernese_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.BERNESE_ENTITY, 0x262627, 0xc27f3a, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ASHEPHERD_SPAWN_EGG = ITEMS.register("ashepherd_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.AUSTRALIAN_SHEPHERD_ENTITY, 0x8d9199, 0x3c3735, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BLOODHOUND_SPAWN_EGG = ITEMS.register("bloodhound_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.BLOODHOUND_ENTITY, 0x472914, 0x2a2726, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> KORNISH_REX_SPAWN_EGG = ITEMS.register("kornish_rex_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.KORNISH_REX_ENTITY, 0x956239, 0x6e3b16, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> COCKER_SPANIEL_SPAWN_EGG = ITEMS.register("cocker_spaniel_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.COCKER_SPANIEL_ENTITY, 0xbc6738, 0x77371c, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> WHIPPET_SPAWN_EGG = ITEMS.register("whippet_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.WHIPPET_ENTITY, 0x9496a1, 0x72737e, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ROTTWEILER_SPAWN_EGG = ITEMS.register("rottweiler_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.ROTTWEILER_ENTITY, 0x232323, 0x8c4716, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_DOG_SPAWN_EGG = ITEMS.register("o_dog_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_DOG_ENTITY, 0x95715f, 0xb8947a, new Item.Properties().stacksTo(64)));

    //Food
    public static final RegistryObject<Item> WOLF = ITEMS.register("wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_WOLF = ITEMS.register("cooked_wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> CAT = ITEMS.register("cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAT = ITEMS.register("cooked_cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> PARROT_THIGH = ITEMS.register("parrot_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_PARROT_THIGH = ITEMS.register("cooked_parrot_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> TROPICAL_FISH_FILLET = ITEMS.register("tropical_fish_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).build())));
    public static final RegistryObject<Item> COOKED_TROPICAL_FISH_FILLET = ITEMS.register("cooked_tropical_fish_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> TROPICAL_FISH_ROE = ITEMS.register("tropical_fish_roe",
            () -> new TropicalFishRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> O_AXOLOTL_BUCKET = ITEMS.register("o_axolotl_bucket",
            () -> new MobBucketItem(POEntityTypes.O_AXOLOTL_ENTITY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_AXOLOTL, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> O_TROPICAL_FISH_BUCKET = ITEMS.register("o_tropical_fish_bucket",
            () -> new MobBucketItem(POEntityTypes.O_TROPICAL_FISH_ENTITY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DOG_SLED = ITEMS.register("dog_sled", DogSledItem::new);


    //Mod Item Tab Icon (UNOBTAINABLE)
    public static final RegistryObject<Item> PETS_OVERHAUL = ITEMS.register("pets_overhaul",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}