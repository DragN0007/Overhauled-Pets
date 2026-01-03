package com.dragn0007.dragnpets.client;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlRender;
import com.dragn0007.dragnpets.entities.cat.OCatRender;
import com.dragn0007.dragnpets.entities.dog.ODogRender;
import com.dragn0007.dragnpets.entities.fox.OFoxRender;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotRender;
import com.dragn0007.dragnpets.entities.parrot.CockatielRender;
import com.dragn0007.dragnpets.entities.parrot.MacawRender;
import com.dragn0007.dragnpets.entities.parrot.RingneckRender;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishRender;
import com.dragn0007.dragnpets.entities.wolf.OWolfRender;
import com.dragn0007.dragnpets.gui.BerneseScreen;
import com.dragn0007.dragnpets.gui.HuskyScreen;
import com.dragn0007.dragnpets.gui.LabradorScreen;
import com.dragn0007.dragnpets.gui.POMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PetsOverhaulClientEvent {

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(POEntityTypes.O_WOLF_ENTITY.get(), OWolfRender::new);
        EntityRenderers.register(POEntityTypes.O_OCELOT_ENTITY.get(), OOcelotRender::new);
        EntityRenderers.register(POEntityTypes.O_FOX_ENTITY.get(), OFoxRender::new);
        EntityRenderers.register(POEntityTypes.O_AXOLOTL_ENTITY.get(), OAxolotlRender::new);
        EntityRenderers.register(POEntityTypes.O_TROPICAL_FISH_ENTITY.get(), OTropicalFishRender::new);
        EntityRenderers.register(POEntityTypes.MACAW_ENTITY.get(), MacawRender::new);
        EntityRenderers.register(POEntityTypes.COCKATIEL_ENTITY.get(), CockatielRender::new);
        EntityRenderers.register(POEntityTypes.RINGNECK_ENTITY.get(), RingneckRender::new);
        EntityRenderers.register(POEntityTypes.O_CAT_ENTITY.get(), OCatRender::new);
        EntityRenderers.register(POEntityTypes.O_DOG_ENTITY.get(), ODogRender::new);
        MenuScreens.register(POMenuTypes.HUSKY_MENU.get(), HuskyScreen::new);
        MenuScreens.register(POMenuTypes.BERNESE_MENU.get(), BerneseScreen::new);
        MenuScreens.register(POMenuTypes.LABRADOR_MENU.get(), LabradorScreen::new);
    }
}