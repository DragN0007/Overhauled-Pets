package com.dragn0007.dragnpets.event;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlRender;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxRender;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotRender;
import com.dragn0007.dragnpets.entities.parrot.*;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishRender;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.entities.wolf.OWolfRender;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PetsOverhaulEvent {

    @SubscribeEvent
    public static void entityAttrbiuteCreationEvent(EntityAttributeCreationEvent event) {
        event.put(EntityTypes.O_WOLF_ENTITY.get(), OWolf.createAttributes().build());
        event.put(EntityTypes.O_OCELOT_ENTITY.get(), OOcelot.createAttributes().build());
        event.put(EntityTypes.O_FOX_ENTITY.get(), OFox.createAttributes().build());
        event.put(EntityTypes.O_AXOLOTL_ENTITY.get(), OAxolotl.createAttributes().build());
        event.put(EntityTypes.O_TROPICAL_FISH_ENTITY.get(), OTropicalFish.createAttributes().build());
        event.put(EntityTypes.MACAW_ENTITY.get(), Macaw.createAttributes().build());
        event.put(EntityTypes.COCKATIEL_ENTITY.get(), Cockatiel.createAttributes().build());
        event.put(EntityTypes.RINGNECK_ENTITY.get(), Ringneck.createAttributes().build());
    }

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypes.O_WOLF_ENTITY.get(), OWolfRender::new);
        EntityRenderers.register(EntityTypes.O_OCELOT_ENTITY.get(), OOcelotRender::new);
        EntityRenderers.register(EntityTypes.O_FOX_ENTITY.get(), OFoxRender::new);
        EntityRenderers.register(EntityTypes.O_AXOLOTL_ENTITY.get(), OAxolotlRender::new);
        EntityRenderers.register(EntityTypes.O_TROPICAL_FISH_ENTITY.get(), OTropicalFishRender::new);
        EntityRenderers.register(EntityTypes.MACAW_ENTITY.get(), MacawRender::new);
        EntityRenderers.register(EntityTypes.COCKATIEL_ENTITY.get(), CockatielRender::new);
        EntityRenderers.register(EntityTypes.RINGNECK_ENTITY.get(), RingneckRender::new);
    }
}