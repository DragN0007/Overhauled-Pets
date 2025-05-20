package com.dragn0007.dragnpets.gui;

import com.dragn0007.dragnpets.entities.dog.ODog;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class LabradorMenu extends AbstractContainerMenu {

    public Container container;
    public ODog labrador;

    public LabradorMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory, new SimpleContainer(extraData.readInt()), (ODog) inventory.player.level().getEntity(extraData.readInt()));
    }

    public LabradorMenu(int containerId, Inventory inventory, Container container, ODog labrador) {
        super(POMenuTypes.LABRADOR_MENU.get(), containerId);
        this.container = container;
        this.labrador = labrador;

        if (this.labrador.isTame()) {
            for(int j = 0; j < 5; ++j) {
                this.addSlot(new Slot(this.container, j, 44 + j * 18, 20));
            }
        }

        int playerSlots = 0;
        for (int x = 0; x < 9; x++) {
            this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 109));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlot(new Slot(inventory, playerSlots++, 8 + x * 18, 51 + y * 18));
            }
        }
    }

        public boolean stillValid(Player player) {
        return this.container.stillValid(player) && this.labrador.isAlive() && this.labrador.distanceTo(player) < 8.0F;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotId) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if(slot.hasItem()) {
            itemStack = slot.getItem().copy();
            int containerSize = this.container.getContainerSize();

            if(slotId < containerSize) {
                if(!this.moveItemStackTo(itemStack, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!this.moveItemStackTo(itemStack, 0, containerSize, false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }
}