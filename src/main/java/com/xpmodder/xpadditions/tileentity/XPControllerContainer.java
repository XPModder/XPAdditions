package com.xpmodder.xpadditions.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class XPControllerContainer extends Container {

    private XPControllerTileEntity te;

    public XPControllerContainer(IInventory playerInv, XPControllerTileEntity te){

        this.te = te;

        // Tile Entity, Slot 0-8, Slot IDs 0-8
        this.addSlotToContainer(new Slot(te, 0, 134, 52));

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

}
