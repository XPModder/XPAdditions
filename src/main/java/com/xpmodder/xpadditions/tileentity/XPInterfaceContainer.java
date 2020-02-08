package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

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

        return this.te.isUsableByPlayer(playerIn);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot < 4) {
                // From TE Inventory to Player Inventory
                if (!this.mergeItemStack(current, 9, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // From Player Inventory to TE Inventory
                if (current.getItem() == Item.getItemFromBlock(ModBlocks.xpBlock)) {
                    if (!this.mergeItemStack(current, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (current.getItem() == Items.BUCKET) {
                    if (!this.mergeItemStack(current, 2, 3, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if(current.getItem() == FluidUtil.getFilledBucket(new FluidStack(ModFluids.fluid_exp, 1000)).getItem()){
                    if (!this.mergeItemStack(current, 3, 4, false)){
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
