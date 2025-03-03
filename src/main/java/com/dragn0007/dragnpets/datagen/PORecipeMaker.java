package com.dragn0007.dragnpets.datagen;

import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, POItems.DOG_SLED.get())
                .define('A', Items.IRON_INGOT)
                .define('B', Items.SPRUCE_PLANKS)
                .define('C', Items.CHEST)
                .define('D', Items.BARREL)
                .pattern("CD ")
                .pattern("BBB")
                .pattern("AAA")
                .unlockedBy("has_iron", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_INGOT).build()))
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