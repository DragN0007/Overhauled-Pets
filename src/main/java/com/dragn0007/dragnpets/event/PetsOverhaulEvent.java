package com.dragn0007.dragnpets.event;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotRender;
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
    }

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityTypes.O_WOLF_ENTITY.get(), OWolfRender::new);
        EntityRenderers.register(EntityTypes.O_OCELOT_ENTITY.get(), OOcelotRender::new);
    }
}