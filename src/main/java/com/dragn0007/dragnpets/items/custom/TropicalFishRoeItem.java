package com.dragn0007.dragnpets.items.custom;

import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFish;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishMarkingLayer;
import com.dragn0007.dragnpets.entities.tropical_fish.OTropicalFishModel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.Random;

public class TropicalFishRoeItem extends Item {

   public TropicalFishRoeItem(Properties properties) {
      super(properties);
   }

   @Override
   public InteractionResult useOn(UseOnContext p_43223_) {
      Level level = p_43223_.getLevel();
      if (!(level instanceof ServerLevel)) {
         return InteractionResult.SUCCESS;
      } else {
         ItemStack itemstack = p_43223_.getItemInHand();
         Player player = p_43223_.getPlayer();
         Vec3 targetPos = player.getEyePosition().add(player.getLookAngle().scale(3));

         spawnFishFry(player, null, EntityTypes.O_TROPICAL_FISH_ENTITY.get(), (ServerLevel) level, targetPos, itemstack);

         itemstack.shrink(1);
         return InteractionResult.CONSUME;
      }
   }

   public Optional<Mob> spawnFishFry(Player player, Mob mob1, EntityType<? extends Mob> p_43218_, ServerLevel level, Vec3 vec3, ItemStack itemStack) {
      OTropicalFish oTropicalFish = EntityTypes.O_TROPICAL_FISH_ENTITY.get().create(level);
      if (oTropicalFish == null) return Optional.empty();
      Random random = new Random();

      oTropicalFish.setAge(-24000);
      oTropicalFish.setVariant(random.nextInt(OTropicalFishModel.Variant.values().length));
      oTropicalFish.setOverlay(random.nextInt(OTropicalFishMarkingLayer.Overlay.values().length));
      oTropicalFish.setSpecies(random.nextInt(OTropicalFish.Species.values().length));

      oTropicalFish.moveTo(vec3.x(), vec3.y() + 1F, vec3.z(), 0.0F, 0.0F);

      level.addFreshEntity(oTropicalFish);

      if (!player.getAbilities().instabuild) {
         itemStack.shrink(1);
      }

      return Optional.of(oTropicalFish);
   }
}