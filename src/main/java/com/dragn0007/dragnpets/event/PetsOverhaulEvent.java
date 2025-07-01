package com.dragn0007.dragnpets.event;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlRender;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.OCatRender;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRex;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoon;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherd;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherdRender;
import com.dragn0007.dragnpets.entities.dog.bernese.Bernese;
import com.dragn0007.dragnpets.entities.dog.bernese.BerneseRender;
import com.dragn0007.dragnpets.entities.dog.bloodhound.Bloodhound;
import com.dragn0007.dragnpets.entities.dog.bloodhound.BloodhoundRender;
import com.dragn0007.dragnpets.entities.dog.border_collie.Collie;
import com.dragn0007.dragnpets.entities.dog.border_collie.CollieRender;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpaniel;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpanielRender;
import com.dragn0007.dragnpets.entities.dog.common.CommonDog;
import com.dragn0007.dragnpets.entities.dog.common.CommonDogRender;
import com.dragn0007.dragnpets.entities.dog.doberman.Doberman;
import com.dragn0007.dragnpets.entities.dog.doberman.DobermanRender;
import com.dragn0007.dragnpets.entities.dog.husky.Husky;
import com.dragn0007.dragnpets.entities.dog.husky.HuskyRender;
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
import com.dragn0007.dragnpets.entities.misc.sled.DogSledModel;
import com.dragn0007.dragnpets.entities.misc.sled.DogSledRender;
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
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetsOverhaulEvent {

    @SubscribeEvent
    public static void entityAttrbiuteCreationEvent(EntityAttributeCreationEvent event) {
        event.put(POEntityTypes.O_WOLF_ENTITY.get(), OWolf.createAttributes().build());
        event.put(POEntityTypes.O_OCELOT_ENTITY.get(), OOcelot.createAttributes().build());
        event.put(POEntityTypes.O_FOX_ENTITY.get(), OFox.createAttributes().build());
        event.put(POEntityTypes.O_AXOLOTL_ENTITY.get(), OAxolotl.createAttributes().build());
        event.put(POEntityTypes.O_TROPICAL_FISH_ENTITY.get(), OTropicalFish.createAttributes().build());
        event.put(POEntityTypes.MACAW_ENTITY.get(), Macaw.createAttributes().build());
        event.put(POEntityTypes.COCKATIEL_ENTITY.get(), Cockatiel.createAttributes().build());
        event.put(POEntityTypes.RINGNECK_ENTITY.get(), Ringneck.createAttributes().build());
        event.put(POEntityTypes.DOBERMAN_ENTITY.get(), Doberman.createAttributes().build());
        event.put(POEntityTypes.O_CAT_ENTITY.get(), OCat.createAttributes().build());
        event.put(POEntityTypes.LABRADOR_ENTITY.get(), Labrador.createAttributes().build());
        event.put(POEntityTypes.HUSKY_ENTITY.get(), Husky.createAttributes().build());
        event.put(POEntityTypes.PYRENEES_ENTITY.get(), Pyrenees.createAttributes().build());
        event.put(POEntityTypes.BORDER_COLLIE_ENTITY.get(), Collie.createAttributes().build());
        event.put(POEntityTypes.MAINE_COON_ENTITY.get(), MaineCoon.createAttributes().build());
        event.put(POEntityTypes.BERNESE_ENTITY.get(), Bernese.createAttributes().build());
        event.put(POEntityTypes.AUSTRALIAN_SHEPHERD_ENTITY.get(), AustralianShepherd.createAttributes().build());
        event.put(POEntityTypes.BLOODHOUND_ENTITY.get(), Bloodhound.createAttributes().build());
        event.put(POEntityTypes.KORNISH_REX_ENTITY.get(), KornishRex.createAttributes().build());
        event.put(POEntityTypes.COCKER_SPANIEL_ENTITY.get(), CockerSpaniel.createAttributes().build());
        event.put(POEntityTypes.WHIPPET_ENTITY.get(), Whippet.createAttributes().build());
        event.put(POEntityTypes.ROTTWEILER_ENTITY.get(), Rottweiler.createAttributes().build());
        event.put(POEntityTypes.O_DOG_ENTITY.get(), CommonDog.createAttributes().build());
    }

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
//        EntityRenderers.register(POEntityTypes.MAINE_COON_ENTITY.get(), MaineCoonRender::new);
        EntityRenderers.register(POEntityTypes.BERNESE_ENTITY.get(), BerneseRender::new);
        EntityRenderers.register(POEntityTypes.AUSTRALIAN_SHEPHERD_ENTITY.get(), AustralianShepherdRender::new);
        EntityRenderers.register(POEntityTypes.BLOODHOUND_ENTITY.get(), BloodhoundRender::new);
//        EntityRenderers.register(POEntityTypes.KORNISH_REX_ENTITY.get(), KornishRexRender::new);
        EntityRenderers.register(POEntityTypes.COCKER_SPANIEL_ENTITY.get(), CockerSpanielRender::new);
        EntityRenderers.register(POEntityTypes.WHIPPET_ENTITY.get(), WhippetRender::new);
        EntityRenderers.register(POEntityTypes.ROTTWEILER_ENTITY.get(), RottweilerRender::new);
        EntityRenderers.register(POEntityTypes.O_DOG_ENTITY.get(), CommonDogRender::new);

        MenuScreens.register(POMenuTypes.HUSKY_MENU.get(), HuskyScreen::new);
        MenuScreens.register(POMenuTypes.BERNESE_MENU.get(), BerneseScreen::new);
        MenuScreens.register(POMenuTypes.LABRADOR_MENU.get(), LabradorScreen::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitionEvent(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DogSledRender.LAYER_LOCATION, DogSledModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerRendererEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(POEntityTypes.DOG_SLED_ENTITY.get(), DogSledRender::new);
    }
}