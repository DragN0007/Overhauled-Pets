package com.dragn0007.dragnpets.common.event;

import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.parrot.Cockatiel;
import com.dragn0007.dragnpets.entities.parrot.Macaw;
import com.dragn0007.dragnpets.entities.parrot.Ringneck;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvent {

    @SubscribeEvent
    public static void onTryCureEntity(PlayerInteractEvent.EntityInteract event) {

        if (event.getTarget() instanceof LivingEntity entity) {
            Player player = event.getEntity();
            ItemStack stack = event.getItemStack();

            if (stack.is(LOItems.MAGNIFYING_GLASS.get())) {
                if (entity instanceof ODog animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+dog/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+dog/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OCat animal) {
                    String breed = animal.getModelResource().toString();
                    String noFillerTextBreed = breed.replaceAll(".+cat/", "");
                    String noUnderscoresTextBreed = noFillerTextBreed.replaceAll("_", " ");
                    String noPNGTextBreed = noUnderscoresTextBreed.replace(".geo.json", "");
                    String breedText = "Breed: " + noPNGTextBreed.toUpperCase();

                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+cat/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(breedText + " | " + coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OAxolotl animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+axolotl/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Morph: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OOcelot animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+ocelot/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OWolf animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+wolf/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof OFox animal) {
                    String coat = animal.getTextureLocation().toString();
                    String noFillerTextCoat = coat.replaceAll(".+fox/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Coat: " + noPNGTextCoat.toUpperCase();

                    String marking = animal.getOverlayLocation().toString();
                    String noFillerTextMarking = marking.replaceAll(".+overlay/", "");
                    String noUnderscoresTextMarking = noFillerTextMarking.replaceAll("_", " ");
                    String noPNGTextMarking = noUnderscoresTextMarking.replace(".png", "");
                    String markingText = "Marking: " + noPNGTextMarking.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText + " | " + markingText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof Cockatiel animal) {
                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+cockatiel/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Morph: " + noPNGTextCoat.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof Macaw animal) {
                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+macaw/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Morph: " + noPNGTextCoat.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText).withStyle(ChatFormatting.GOLD), true);
                }

                if (entity instanceof Ringneck animal) {
                    String coat = animal.getTextureResource().toString();
                    String noFillerTextCoat = coat.replaceAll(".+ringneck/", "");
                    String noUnderscoresTextCoat = noFillerTextCoat.replaceAll("_", " ");
                    String noPNGTextCoat = noUnderscoresTextCoat.replace(".png", "");
                    String coatText = "Morph: " + noPNGTextCoat.toUpperCase();

                    player.displayClientMessage(Component.translatable(coatText).withStyle(ChatFormatting.GOLD), true);
                }
            }
        }
    }


}