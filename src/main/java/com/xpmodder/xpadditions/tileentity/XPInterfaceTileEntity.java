package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.Buckets;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

import javax.annotation.Nullable;

public class XPInterfaceTileEntity extends ModBaseTileEntity implements IInventory,ITickable{

    private ItemStack[] inventory;
    private String newName = "container.xp_interface_tile_entity";
    public int xp;
    public int maxXP;


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

    public boolean isSlotOccupied(int index){

        try{

            if (this.getStackInSlot(index).stackSize > 0){

                return true;

            }
            else{

                return false;

            }

        }
        catch (NullPointerException e){

            return false;

        }

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

    @Override
    public void updateChildren() {

        try {

            xp = ((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).getTotalXP(((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).getID());

            int inSlot = 0;

            if (isSlotOccupied(0)){

                inSlot = this.getStackInSlot(0).stackSize;

            }

            if (xp >= XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)) & inSlot < this.getInventoryStackLimit()){

                this.setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, inSlot + 1));
                ((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).removeXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), ((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).getID());

            }

            maxXP = XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)) * this.getInventoryStackLimit();

            if (this.isSlotOccupied(1)) {

                if (this.getStackInSlot(1).getItem() == Item.getItemFromBlock(ModBlocks.xpBlock)) {

                    int amount = XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)) * this.getStackInSlot(1).stackSize;
                    this.setInventorySlotContents(1, new ItemStack(ModBlocks.xpBlock, 0));
                    ((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).addXP(amount, ((XPControllerTileEntity) worldObj.getTileEntity(this.controller)).getID());

                }

            }

        }
        catch (NullPointerException e){

            LogHelper.error("Error: NullPointerException in XPInterfaceTileEntity.updateChildren() !");

        }

    }
}
