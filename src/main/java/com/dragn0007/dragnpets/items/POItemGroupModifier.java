package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnlivestock.items.LOItemGroup;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;

public class POItemGroupModifier {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PetsOverhaul.MODID);

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent output) {
        if(output.getTabKey() == LOItemGroup.LIVESTOCK_OVERHAUL_GROUP.getKey()) {

            output.accept(POItems.O_WOLF_SPAWN_EGG.get());

        }
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
