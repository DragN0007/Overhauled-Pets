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

                        output.accept(POItems.MACAW_SPAWN_EGG.get());
                        output.accept(POItems.COCKATIEL_SPAWN_EGG.get());
                        output.accept(POItems.RINGNECK_SPAWN_EGG.get());

                        output.accept(POItems.WOLF.get());
                        output.accept(POItems.COOKED_WOLF.get());
                        output.accept(POItems.CAT.get());
                        output.accept(POItems.COOKED_CAT.get());
                        output.accept(POItems.PARROT_THIGH.get());
                        output.accept(POItems.COOKED_PARROT_THIGH.get());

                        output.accept(POItems.O_AXOLOTL_BUCKET.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}


