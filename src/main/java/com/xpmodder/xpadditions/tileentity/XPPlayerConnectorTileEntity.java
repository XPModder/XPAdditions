package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.utility.EnumRSMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class XPPlayerConnectorTileEntity extends ModBaseTileEntity implements IInventory,ITickable {

    private ItemStack[] inventory;

    public XPPlayerConnectorTileEntity() {

        this.inventory = new ItemStack[this.getSizeInventory()];

    }

    @Override
    public NBTTagCompound childWriteNBT(NBTTagCompound compound) {
        return compound;
    }

    @Override
    public void childReadNBT(NBTTagCompound compound) {

    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {

        if (index < 0 || index >= this.getSizeInventory())
            return ItemStack.EMPTY;
        return this.inventory[index];

    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        if (!this.getStackInSlot(index).isEmpty()) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, ItemStack.EMPTY);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0) {
                    this.setInventorySlotContents(index, ItemStack.EMPTY);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return ItemStack.EMPTY;
        }

    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return getStackInSlot(index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (!stack.isEmpty() && stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (!stack.isEmpty() && stack.getCount() == 0)
            stack = ItemStack.EMPTY;

        this.inventory[index] = stack;
        this.markDirty();

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer playerIn){

        return this.world.getTileEntity(this.getPos()) == this && playerIn.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;

    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
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
            this.setInventorySlotContents(i, ItemStack.EMPTY);

    }

    @Override
    public void updateChildren() {

    }

    private boolean shouldRun(){

        if(this.RSInt == EnumRSMode.REDSTONE_IGNORED.getID())
            return true;
        else if (this.RSInt == EnumRSMode.REDSTONE_OFF.getID() && this.world.isBlockPowered(this.pos))
            return true;
        else if (this.RSInt == EnumRSMode.REDSTONE_ON.getID() && !this.world.isBlockPowered(this.pos))
            return true;
        else
            return false;

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
