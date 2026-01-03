package com.dragn0007.dragnpets.event;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.parrot.Cockatiel;
import com.dragn0007.dragnpets.entities.parrot.Macaw;
import com.dragn0007.dragnpets.entities.parrot.Ringneck;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


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
        event.put(POEntityTypes.O_CAT_ENTITY.get(), OCat.createAttributes().build());
        event.put(POEntityTypes.O_DOG_ENTITY.get(), ODog.createAttributes().build());
    }

    @SubscribeEvent
    public static void spawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
        event.register(POEntityTypes.O_WOLF_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OWolf::checkPredatorSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
     }
}