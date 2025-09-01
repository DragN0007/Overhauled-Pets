package com.dragn0007.dragnpets.datagen;

import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class PORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public PORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, LOItems.DOG_SLED.get())
                .define('#', Items.IRON_INGOT)
                .define('A', LOItems.WAGON_BODY.get())
                .define('C', Items.CHEST)
                .pattern("#AC")
                .pattern("###")
                .unlockedBy("has_wood", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.PLANKS).build()))
                .save(pFinishedRecipeConsumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.LEATHER_DOG_ARMOR.get())
                .define('A', Items.LEATHER)
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.CHAINMAIL_DOG_ARMOR.get())
                .define('A', Items.CHAIN)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.IRON_DOG_ARMOR.get())
                .define('A', Items.IRON_INGOT)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.COPPER_DOG_ARMOR.get())
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.RIOT_DOG_ARMOR.get())
                .define('A', Items.BLACK_WOOL)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.QUARTZ_DOG_ARMOR.get())
                .define('A', Items.QUARTZ)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.GOLD_DOG_ARMOR.get())
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.EMERALD_DOG_ARMOR.get())
                .define('A', Items.EMERALD)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.DIAMOND_DOG_ARMOR.get())
                .define('A', Items.DIAMOND)
                .define('B', Items.LEATHER)
                .pattern("AAA")
                .pattern("ABA")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.LEATHER).build()))
                .save(pFinishedRecipeConsumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.BLACK_VEST.get())
                .define('A', Items.BLACK_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.BLUE_VEST.get())
                .define('A', Items.BLUE_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.BROWN_VEST.get())
                .define('A', Items.BROWN_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.CYAN_VEST.get())
                .define('A', Items.CYAN_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.GREEN_VEST.get())
                .define('A', Items.GREEN_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.GREY_VEST.get())
                .define('A', Items.GRAY_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.LIGHT_BLUE_VEST.get())
                .define('A', Items.LIGHT_BLUE_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.LIGHT_GREY_VEST.get())
                .define('A', Items.LIGHT_GRAY_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.LIME_VEST.get())
                .define('A', Items.LIME_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.MAGENTA_VEST.get())
                .define('A', Items.MAGENTA_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.ORANGE_VEST.get())
                .define('A', Items.ORANGE_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.PINK_VEST.get())
                .define('A', Items.PINK_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.PURPLE_VEST.get())
                .define('A', Items.PURPLE_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.RED_VEST.get())
                .define('A', Items.RED_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.WHITE_VEST.get())
                .define('A', Items.WHITE_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.YELLOW_VEST.get())
                .define('A', Items.YELLOW_DYE)
                .define('B', ItemTags.WOOL)
                .pattern("ABB")
                .unlockedBy("has_wool", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ItemTags.WOOL).build()))
                .save(pFinishedRecipeConsumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, POItems.TROPICAL_FISH_FILLET.get(), 2)
                .requires(Items.TROPICAL_FISH)
                .unlockedBy("has_fish", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.TROPICAL_FISH)
                        .build()))
                .save(pFinishedRecipeConsumer);

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 100)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 200)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 600)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 100)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 200)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 600)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.PARROT_THIGH.get()), RecipeCategory.MISC, POItems.COOKED_PARROT_THIGH.get(), 0.35F, 100)
                .unlockedBy("has_parrot", has(POItems.PARROT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_parrot_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.PARROT_THIGH.get()), RecipeCategory.MISC, POItems.COOKED_PARROT_THIGH.get(), 0.35F, 200)
                .unlockedBy("has_parrot", has(POItems.PARROT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_parrot_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.PARROT_THIGH.get()), RecipeCategory.MISC, POItems.COOKED_PARROT_THIGH.get(), 0.35F, 600)
                .unlockedBy("has_parrot", has(POItems.PARROT_THIGH.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_parrot_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.TROPICAL_FISH_FILLET.get()), RecipeCategory.MISC, POItems.COOKED_TROPICAL_FISH_FILLET.get(), 0.35F, 100)
                .unlockedBy("has_tropical_fish_fillet", has(POItems.TROPICAL_FISH_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_tropical_fish_fillet_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.TROPICAL_FISH_FILLET.get()), RecipeCategory.MISC, POItems.COOKED_TROPICAL_FISH_FILLET.get(), 0.35F, 200)
                .unlockedBy("has_tropical_fish_fillet", has(POItems.TROPICAL_FISH_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_tropical_fish_fillet_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.TROPICAL_FISH_FILLET.get()), RecipeCategory.MISC, POItems.COOKED_TROPICAL_FISH_FILLET.get(), 0.35F, 600)
                .unlockedBy("has_tropical_fish_fillet", has(POItems.TROPICAL_FISH_FILLET.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_tropical_fish_fillet_campfire_cooking"));
    }
}