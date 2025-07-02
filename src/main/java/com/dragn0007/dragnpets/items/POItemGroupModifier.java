package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class POItemGroupModifier {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LivestockOverhaul.MODID);

    public static final RegistryObject<CreativeModeTab> PETS_OVERHAUL_GROUP = CREATIVE_MODE_TABS.register("overhauled_pets",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(POItems.PETS_OVERHAUL.get())).title(Component.translatable("itemGroup.overhauled_pets"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(POItems.O_WOLF_SPAWN_EGG.get());
                        output.accept(POItems.O_OCELOT_SPAWN_EGG.get());
                        output.accept(POItems.O_FOX_SPAWN_EGG.get());
                        output.accept(POItems.O_AXOLOTL_SPAWN_EGG.get());
                        output.accept(POItems.O_TROPICAL_FISH_SPAWN_EGG.get());
                        output.accept(POItems.MACAW_SPAWN_EGG.get());
                        output.accept(POItems.COCKATIEL_SPAWN_EGG.get());
                        output.accept(POItems.RINGNECK_SPAWN_EGG.get());
                        output.accept(POItems.O_CAT_SPAWN_EGG.get());
                        output.accept(POItems.MAINE_COON_SPAWN_EGG.get());
                        output.accept(POItems.KORNISH_REX_SPAWN_EGG.get());
                        output.accept(POItems.O_DOG_SPAWN_EGG.get());
                        output.accept(POItems.BORDER_COLLIE_SPAWN_EGG.get());
                        output.accept(POItems.PYRENEES_SPAWN_EGG.get());
                        output.accept(POItems.HUSKY_SPAWN_EGG.get());
                        output.accept(POItems.LABRADOR_SPAWN_EGG.get());
                        output.accept(POItems.DOBERMAN_SPAWN_EGG.get());
                        output.accept(POItems.BERNESE_SPAWN_EGG.get());
                        output.accept(POItems.ASHEPHERD_SPAWN_EGG.get());
                        output.accept(POItems.BLOODHOUND_SPAWN_EGG.get());
                        output.accept(POItems.COCKER_SPANIEL_SPAWN_EGG.get());
                        output.accept(POItems.WHIPPET_SPAWN_EGG.get());
                        output.accept(POItems.ROTTWEILER_SPAWN_EGG.get());

                        output.accept(POItems.WOLF.get());
                        output.accept(POItems.COOKED_WOLF.get());
                        output.accept(POItems.CAT.get());
                        output.accept(POItems.COOKED_CAT.get());
                        output.accept(POItems.PARROT_THIGH.get());
                        output.accept(POItems.COOKED_PARROT_THIGH.get());
                        output.accept(POItems.TROPICAL_FISH_FILLET.get());
                        output.accept(POItems.COOKED_TROPICAL_FISH_FILLET.get());

                        output.accept(POItems.TROPICAL_FISH_ROE.get());

                        output.accept(POItems.O_AXOLOTL_BUCKET.get());
                        output.accept(POItems.O_TROPICAL_FISH_BUCKET.get());

                        //TODO;
//                        output.accept(POItems.DOG_SLED.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}


