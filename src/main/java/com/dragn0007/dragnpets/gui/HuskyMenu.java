package com.dragn0007.dragnpets.gui;

import com.dragn0007.dragnpets.entities.dog.Husky;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class HuskyMenu extends AbstractContainerMenu {

    public Container container;
    public Husky husky;

    public HuskyMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (Husky) inventory.player.level().getEntity(extraData.readInt()));
    }

    public HuskyMenu(int containerId, Inventory inventory, Container container, Husky husky) {
        super(POMenuTypes.HUSKY_MENU.get(), containerId);
        this.container = container;
        this.husky = husky;

        int huskySlots = 0;

        if (this.husky.hasChest()) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 4; x++) {
                    this.addSlot(new Slot(this.container, huskySlots++, 58 + x * 18, 18 + y * 18));
                }
            }
        }

        int playerSlots = 0;
        for (int x = 0; x < 9; x++) {
            this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 142));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 84 + y * 18));
            }
        }
    }

        public boolean stillValid(Player player) {
        return this.container.stillValid(player) && this.husky.isAlive() && this.husky.distanceTo(player) < 8.0F;
    }

    public ItemStack quickMoveStack(Player player, int slotId) {
        Slot slot = this.slots.get(slotId);
        if(!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = slot.getItem();
        ItemStack itemStackCopy = itemStack.copy();
        int containerSize = this.container.getContainerSize();

        if(slotId < containerSize) {
            if(!this.moveItemStackTo(itemStack, containerSize, containerSize + 36, true)) {
                return ItemStack.EMPTY;
            }
        } else if(slotId < containerSize + 36) {
            if(!this.moveItemStackTo(itemStack, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }
        }

        if(itemStack.getCount() == 0) {
            slot.set(ItemStack.EMPTY);
        }
        slot.setChanged();
        return itemStackCopy;
    }
}