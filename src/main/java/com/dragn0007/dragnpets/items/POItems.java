package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.items.custom.DogArmorItem;
import com.dragn0007.dragnpets.items.custom.TropicalFishRoeItem;
import com.dragn0007.dragnpets.items.custom.VestItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
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
    public static final RegistryObject<Item> O_DOG_SPAWN_EGG = ITEMS.register("o_dog_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.O_DOG_ENTITY, 0x95715f, 0xb8947a, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MACAW_SPAWN_EGG = ITEMS.register("macaw_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.MACAW_ENTITY, 0xab2f23, 0xda9e35, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> COCKATIEL_SPAWN_EGG = ITEMS.register("cockatiel_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.COCKATIEL_ENTITY, 0x9d9d9d, 0xfff469, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RINGNECK_SPAWN_EGG = ITEMS.register("ringneck_spawn_egg",
            () -> new ForgeSpawnEggItem(POEntityTypes.RINGNECK_ENTITY, 0xa3a855, 0x2f3515, new Item.Properties().stacksTo(64)));

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


    public static final RegistryObject<Item> LEATHER_DOG_ARMOR = ITEMS.register("leather_dog_armor",
            () -> new DogArmorItem(2, "leather", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CHAINMAIL_DOG_ARMOR = ITEMS.register("chainmail_dog_armor",
            () -> new DogArmorItem(3, "chainmail", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> COPPER_DOG_ARMOR = ITEMS.register("copper_dog_armor",
            () -> new DogArmorItem(3, "copper", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> IRON_DOG_ARMOR = ITEMS.register("iron_dog_armor",
            () -> new DogArmorItem(4, "iron", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> RIOT_DOG_ARMOR = ITEMS.register("riot_dog_armor",
            () -> new DogArmorItem(4, "riot", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> QUARTZ_DOG_ARMOR = ITEMS.register("quartz_dog_armor",
            () -> new DogArmorItem(5, "quartz", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> GOLD_DOG_ARMOR = ITEMS.register("gold_dog_armor",
            () -> new DogArmorItem(6, "gold", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> EMERALD_DOG_ARMOR = ITEMS.register("emerald_dog_armor",
            () -> new DogArmorItem(8, "emerald", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> DIAMOND_DOG_ARMOR = ITEMS.register("diamond_dog_armor",
            () -> new DogArmorItem(8, "diamond", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_DOG_ARMOR = ITEMS.register("netherite_dog_armor",
            () -> new DogArmorItem(10, "netherite", (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> OBSIDIAN_DOG_ARMOR = ITEMS.register("obsidian_dog_armor",
            () -> new DogArmorItem(12, "obsidian", (new Item.Properties()).stacksTo(1)));

    public static final RegistryObject<Item> BLACK_VEST = ITEMS.register("black_vest",
            () -> new VestItem(DyeColor.BLACK, new Item.Properties()));
    public static final RegistryObject<Item> BLUE_VEST = ITEMS.register("blue_vest",
            () -> new VestItem(DyeColor.BLUE, new Item.Properties()));
    public static final RegistryObject<Item> BROWN_VEST = ITEMS.register("brown_vest",
            () -> new VestItem(DyeColor.BROWN, new Item.Properties()));
    public static final RegistryObject<Item> CYAN_VEST = ITEMS.register("cyan_vest",
            () -> new VestItem(DyeColor.CYAN, new Item.Properties()));
    public static final RegistryObject<Item> GREEN_VEST = ITEMS.register("green_vest",
            () -> new VestItem(DyeColor.GREEN, new Item.Properties()));
    public static final RegistryObject<Item> GREY_VEST = ITEMS.register("grey_vest",
            () -> new VestItem(DyeColor.GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_VEST = ITEMS.register("light_blue_vest",
            () -> new VestItem(DyeColor.LIGHT_BLUE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GREY_VEST = ITEMS.register("light_grey_vest",
            () -> new VestItem(DyeColor.LIGHT_GRAY, new Item.Properties()));
    public static final RegistryObject<Item> LIME_VEST = ITEMS.register("lime_vest",
            () -> new VestItem(DyeColor.LIME, new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_VEST = ITEMS.register("magenta_vest",
            () -> new VestItem(DyeColor.MAGENTA, new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_VEST = ITEMS.register("orange_vest",
            () -> new VestItem(DyeColor.ORANGE, new Item.Properties()));
    public static final RegistryObject<Item> PINK_VEST = ITEMS.register("pink_vest",
            () -> new VestItem(DyeColor.PINK, new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_VEST = ITEMS.register("purple_vest",
            () -> new VestItem(DyeColor.PURPLE, new Item.Properties()));
    public static final RegistryObject<Item> RED_VEST = ITEMS.register("red_vest",
            () -> new VestItem(DyeColor.RED, new Item.Properties()));
    public static final RegistryObject<Item> WHITE_VEST = ITEMS.register("white_vest",
            () -> new VestItem(DyeColor.WHITE, new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_VEST = ITEMS.register("yellow_vest",
            () -> new VestItem(DyeColor.YELLOW, new Item.Properties()));

    //Mod Item Tab Icon (UNOBTAINABLE)
    public static final RegistryObject<Item> PETS_OVERHAUL = ITEMS.register("pets_overhaul",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}