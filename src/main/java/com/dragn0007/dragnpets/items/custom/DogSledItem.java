package com.dragn0007.dragnpets.items.custom;

import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.misc.sled.DogSled;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class DogSledItem extends Item {

    public DogSledItem() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if(blockHitResult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemStack);
        } else if (level.isClientSide) {
            return InteractionResultHolder.success(itemStack);
        } else {
            BlockPos pos = blockHitResult.getBlockPos();
            if(level.getBlockState(pos).getBlock() instanceof LiquidBlock) {
                return InteractionResultHolder.pass(itemStack);
            } else if (level.mayInteract(player, pos) && player.mayUseItemAt(pos, blockHitResult.getDirection(), itemStack)) {
                DogSled dogSled = EntityTypes.DOG_SLED_ENTITY.get().spawn((ServerLevel) level, itemStack, player, pos.above(), MobSpawnType.SPAWN_EGG, false, false);
                if(dogSled == null) {
                    return InteractionResultHolder.pass(itemStack);
                } else {
                    if(!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, dogSled.position());
                    return InteractionResultHolder.consume(itemStack);
                }
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }
    }
}
