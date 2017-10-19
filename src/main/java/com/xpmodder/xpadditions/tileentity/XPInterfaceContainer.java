package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.Buckets;
import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class XPInterfaceContainer extends Container {

    private XPInterfaceTileEntity te;

    public XPInterfaceContainer(IInventory playerInv, XPInterfaceTileEntity te){

        this.te = te;

        // Tile Entity, Slot 0-8, Slot IDs 0-8
        this.addSlotToContainer(new Slot(te, 0, 8, 50));
        this.addSlotToContainer(new Slot(te, 1, 26, 50));
        this.addSlotToContainer(new Slot(te, 2, 134, 50));
        this.addSlotToContainer(new Slot(te, 3, 152, 50));

        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {

        return this.te.isUseableByPlayer(playerIn);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 4) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 9, 40, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (current.getItem() == ModBlocks.xpBlock.getDefaultState())
                    if (!this.mergeItemStack(current, 1, 1, false))
                        return null;
                else if (current.getItem() == Buckets.itemBucketLiquidXP.getItem())
                    if (!this.mergeItemStack(current, 3, 3, false))
                        return null;
                else
                    return null;
            }

            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }

}
