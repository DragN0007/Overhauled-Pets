package com.dragn0007.dragnpets.gui;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class POMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, PetsOverhaul.MODID);

    public static final RegistryObject<MenuType<HuskyMenu>> HUSKY_MENU = registerMenuType("husky_menu", HuskyMenu::new);
    public static final RegistryObject<MenuType<BerneseMenu>> BERNESE_MENU = registerMenuType("bernese_menu", BerneseMenu::new);
    public static final RegistryObject<MenuType<LabradorMenu>> LABRADOR_MENU = registerMenuType("labrador_menu", LabradorMenu::new);

    public static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENU_TYPES.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
