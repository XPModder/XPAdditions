package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.Buckets;
import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class XPInterfaceTileEntity extends TileEntity implements IInventory{

    private ItemStack[] inventory;
    private String newName = "container.xp_interface_tile_entity";

    public XPInterfaceTileEntity(){

        this.inventory = new ItemStack[this.getSizeInventory()];

    }

    public String getCustomName() {

        return this.newName;

    }

    public void setCustomName(String customName) {

        this.newName = customName;

    }

    @Override
    public int getSizeInventory() {

        return 5;

    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {

        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.inventory[index];

    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {

        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }

    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {

        return null;

    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {

        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.inventory[index] = stack;
        this.markDirty();

    }

    @Override
    public int getInventoryStackLimit() {

        return 64;

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;

    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        if (index == 0 || index == 1) {

            if (stack.getItem() == ModBlocks.xpBlock.getDefaultState())
                return true;

        }
        else if (index == 3){

            if (stack.getItem() == Buckets.itemBucketLiquidXP.getItem())
                return true;

        }

        return false;

    }

    @Override
    public int getField(int id) {

        return 0;

    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {

        return 0;

    }

    @Override
    public void clear() {

        for (int i = 0; i < this.getSizeInventory(); i++)
            this.setInventorySlotContents(i, null);

    }

    @Override
    public String getName() {

        return this.newName;

    }

    @Override
    public boolean hasCustomName() {

        return this.newName != null && !this.newName.equals("");

    }

}
