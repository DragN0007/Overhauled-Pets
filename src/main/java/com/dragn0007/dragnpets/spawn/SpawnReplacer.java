package com.dragn0007.dragnpets.spawn;

import com.dragn0007.dragnlivestock.entities.cow.OCowModel;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PetsOverhaul.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpawnReplacer {

    // This class falls under the LGPL license, as stated in the CODE_LICENSE.txt
    // Some of this code was referenced from Realistic Horse Genetics. Please check them out, too! :)
    // https://github.com/sekelsta/horse-colors  |  https://www.curseforge.com/minecraft/mc-mods/realistic-horse-genetics

    @SubscribeEvent
    public static void onSpawn(EntityJoinLevelEvent event) {

        //Wolf
        if (!LivestockOverhaulCommonConfig.FAILSAFE_REPLACER.get() && PetsOverhaulCommonConfig.REPLACE_WOLVES.get() && event.getEntity() instanceof Wolf) {
            Wolf vanillaWolf = (Wolf) event.getEntity();

            if (event.getLevel().isClientSide) {
                return;
            }

            OWolf oWolf = EntityTypes.O_WOLF_ENTITY.get().create(event.getLevel());
            if (oWolf != null) {
                oWolf.copyPosition(vanillaWolf);

                oWolf.setCustomName(vanillaWolf.getCustomName());
                oWolf.setAge(vanillaWolf.getAge());

                int randomVariant = event.getLevel().getRandom().nextInt(OCowModel.Variant.values().length);
                oWolf.setVariant(randomVariant);

                if (event.getLevel().isClientSide) {
                    vanillaWolf.remove(Entity.RemovalReason.DISCARDED);
                }

                event.getLevel().addFreshEntity(oWolf);
                vanillaWolf.remove(Entity.RemovalReason.DISCARDED);

                event.setCanceled(true);
            }
        }


    }

}