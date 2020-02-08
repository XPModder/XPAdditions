package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class XPControllerContainer extends Container {

    private XPControllerTileEntity te;

    public XPControllerContainer(IInventory playerInv, XPControllerTileEntity te){

        this.te = te;

        // Tile Entity, Slot 0, Slot IDs 0
        this.addSlotToContainer(new Slot(te, 0, 134, 52));

        // Player Inventory, Slot 9-35, Slot IDs 1-26
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 27-36
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {

        return this.te.isUsableByPlayer(playerIn);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 2) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 9, 37, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // From Player Inventory to TE Inventory
                if (current.getItem() == Item.getItemFromBlock(ModBlocks.xpInterfaceBlock)) {
                    if (!this.mergeItemStack(current, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (current.getItem() == Item.getItemFromBlock(ModBlocks.xpPlayerConnectorBlock)) {
                    if (!this.mergeItemStack(current, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return ItemStack.EMPTY;
            //slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

}
