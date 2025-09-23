package com.dragn0007.dragnpets.client;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlRender;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.OCatRender;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRex;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRexRender;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoon;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoonRender;
import com.dragn0007.dragnpets.entities.cat.manx.Manx;
import com.dragn0007.dragnpets.entities.cat.manx.ManxRender;
import com.dragn0007.dragnpets.entities.dog.CommonDog;
import com.dragn0007.dragnpets.entities.dog.CommonDogRender;
import com.dragn0007.dragnpets.entities.dog.american_ridgeback.AmericanRidgeback;
import com.dragn0007.dragnpets.entities.dog.american_ridgeback.AmericanRidgebackRender;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherd;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherdRender;
import com.dragn0007.dragnpets.entities.dog.beagle.Beagle;
import com.dragn0007.dragnpets.entities.dog.beagle.BeagleRender;
import com.dragn0007.dragnpets.entities.dog.bernese.Bernese;
import com.dragn0007.dragnpets.entities.dog.bernese.BerneseRender;
import com.dragn0007.dragnpets.entities.dog.bloodhound.Bloodhound;
import com.dragn0007.dragnpets.entities.dog.bloodhound.BloodhoundRender;
import com.dragn0007.dragnpets.entities.dog.border_collie.Collie;
import com.dragn0007.dragnpets.entities.dog.border_collie.CollieRender;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpaniel;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpanielRender;
import com.dragn0007.dragnpets.entities.dog.coonhound.Coonhound;
import com.dragn0007.dragnpets.entities.dog.coonhound.CoonhoundRender;
import com.dragn0007.dragnpets.entities.dog.doberman.Doberman;
import com.dragn0007.dragnpets.entities.dog.doberman.DobermanRender;
import com.dragn0007.dragnpets.entities.dog.foxhound.Foxhound;
import com.dragn0007.dragnpets.entities.dog.foxhound.FoxhoundRender;
import com.dragn0007.dragnpets.entities.dog.husky.Husky;
import com.dragn0007.dragnpets.entities.dog.husky.HuskyRender;
import com.dragn0007.dragnpets.entities.dog.jack_russell.JackRussell;
import com.dragn0007.dragnpets.entities.dog.jack_russell.JackRussellRender;
import com.dragn0007.dragnpets.entities.dog.labrador.Labrador;
import com.dragn0007.dragnpets.entities.dog.labrador.LabradorRender;
import com.dragn0007.dragnpets.entities.dog.pyrenees.Pyrenees;
import com.dragn0007.dragnpets.entities.dog.pyrenees.PyreneesRender;
import com.dragn0007.dragnpets.entities.dog.rottweiler.Rottweiler;
import com.dragn0007.dragnpets.entities.dog.rottweiler.RottweilerRender;
import com.dragn0007.dragnpets.entities.dog.whippet.Whippet;
import com.dragn0007.dragnpets.entities.dog.whippet.WhippetRender;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxRender;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotRender;
import com.dragn0007.dragnpets.entities.parrot.*;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishRender;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.entities.wolf.OWolfRender;
import com.dragn0007.dragnpets.gui.BerneseScreen;
import com.dragn0007.dragnpets.gui.HuskyScreen;
import com.dragn0007.dragnpets.gui.LabradorScreen;
import com.dragn0007.dragnpets.gui.POMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
        EntityRenderers.register(POEntityTypes.DOBERMAN_ENTITY.get(), DobermanRender::new);
        EntityRenderers.register(POEntityTypes.O_CAT_ENTITY.get(), OCatRender::new);
        EntityRenderers.register(POEntityTypes.LABRADOR_ENTITY.get(), LabradorRender::new);
        EntityRenderers.register(POEntityTypes.HUSKY_ENTITY.get(), HuskyRender::new);
        EntityRenderers.register(POEntityTypes.PYRENEES_ENTITY.get(), PyreneesRender::new);
        EntityRenderers.register(POEntityTypes.BORDER_COLLIE_ENTITY.get(), CollieRender::new);
        EntityRenderers.register(POEntityTypes.MAINE_COON_ENTITY.get(), MaineCoonRender::new);
        EntityRenderers.register(POEntityTypes.BERNESE_ENTITY.get(), BerneseRender::new);
        EntityRenderers.register(POEntityTypes.AUSTRALIAN_SHEPHERD_ENTITY.get(), AustralianShepherdRender::new);
        EntityRenderers.register(POEntityTypes.BLOODHOUND_ENTITY.get(), BloodhoundRender::new);
        EntityRenderers.register(POEntityTypes.KORNISH_REX_ENTITY.get(), KornishRexRender::new);
        EntityRenderers.register(POEntityTypes.COCKER_SPANIEL_ENTITY.get(), CockerSpanielRender::new);
        EntityRenderers.register(POEntityTypes.WHIPPET_ENTITY.get(), WhippetRender::new);
        EntityRenderers.register(POEntityTypes.ROTTWEILER_ENTITY.get(), RottweilerRender::new);
        EntityRenderers.register(POEntityTypes.O_DOG_ENTITY.get(), CommonDogRender::new);
        EntityRenderers.register(POEntityTypes.MANX_ENTITY.get(), ManxRender::new);
        EntityRenderers.register(POEntityTypes.AMERICAN_RIDGEBACK_ENTITY.get(), AmericanRidgebackRender::new);
        EntityRenderers.register(POEntityTypes.BEAGLE_ENTITY.get(), BeagleRender::new);
        EntityRenderers.register(POEntityTypes.COONHOUND_ENTITY.get(), CoonhoundRender::new);
        EntityRenderers.register(POEntityTypes.FOXHOUND_ENTITY.get(), FoxhoundRender::new);
        EntityRenderers.register(POEntityTypes.JACK_RUSSELL_ENTITY.get(), JackRussellRender::new);

        MenuScreens.register(POMenuTypes.HUSKY_MENU.get(), HuskyScreen::new);
        MenuScreens.register(POMenuTypes.BERNESE_MENU.get(), BerneseScreen::new);
        MenuScreens.register(POMenuTypes.LABRADOR_MENU.get(), LabradorScreen::new);
    }
}