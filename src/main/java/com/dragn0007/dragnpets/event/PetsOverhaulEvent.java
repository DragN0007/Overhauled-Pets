package com.dragn0007.dragnpets.event;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.kornish_rex.KornishRex;
import com.dragn0007.dragnpets.entities.cat.maine_coon.MaineCoon;
import com.dragn0007.dragnpets.entities.cat.manx.Manx;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.dog.american_ridgeback.AmericanRidgeback;
import com.dragn0007.dragnpets.entities.dog.australian_shepherd.AustralianShepherd;
import com.dragn0007.dragnpets.entities.dog.beagle.Beagle;
import com.dragn0007.dragnpets.entities.dog.bernese.Bernese;
import com.dragn0007.dragnpets.entities.dog.bloodhound.Bloodhound;
import com.dragn0007.dragnpets.entities.dog.border_collie.Collie;
import com.dragn0007.dragnpets.entities.dog.cocker_spaniel.CockerSpaniel;
import com.dragn0007.dragnpets.entities.dog.coonhound.Coonhound;
import com.dragn0007.dragnpets.entities.dog.doberman.Doberman;
import com.dragn0007.dragnpets.entities.dog.foxhound.Foxhound;
import com.dragn0007.dragnpets.entities.dog.husky.Husky;
import com.dragn0007.dragnpets.entities.dog.jack_russell.JackRussell;
import com.dragn0007.dragnpets.entities.dog.labrador.Labrador;
import com.dragn0007.dragnpets.entities.dog.pyrenees.Pyrenees;
import com.dragn0007.dragnpets.entities.dog.rottweiler.Rottweiler;
import com.dragn0007.dragnpets.entities.dog.whippet.Whippet;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.parrot.*;
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
        event.put(POEntityTypes.O_DOG_ENTITY.get(), ODog.createAttributes().build());
        event.put(POEntityTypes.MANX_ENTITY.get(), Manx.createAttributes().build());
        event.put(POEntityTypes.AMERICAN_RIDGEBACK_ENTITY.get(), AmericanRidgeback.createAttributes().build());
        event.put(POEntityTypes.BEAGLE_ENTITY.get(), Beagle.createAttributes().build());
        event.put(POEntityTypes.COONHOUND_ENTITY.get(), Coonhound.createAttributes().build());
        event.put(POEntityTypes.FOXHOUND_ENTITY.get(), Foxhound.createAttributes().build());
        event.put(POEntityTypes.JACK_RUSSELL_ENTITY.get(), JackRussell.createAttributes().build());
    }

    @SubscribeEvent
    public static void spawnPlacementRegisterEvent(SpawnPlacementRegisterEvent event) {
        event.register(POEntityTypes.O_WOLF_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OWolf::checkPredatorSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
     }
}