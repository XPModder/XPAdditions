package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.Buckets;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sun.rmi.runtime.Log;

import javax.annotation.Nullable;
import java.util.Arrays;

public class XPInterfaceTileEntity extends ModBaseTileEntity implements IInventory,ITickable{

    private ItemStack[] inventory;
    private String newName = "container.xp_interface_tile_entity";
    public int xp = 0;
    public int maxXP;


    public XPInterfaceTileEntity(){

        this.inventory = new ItemStack[this.getSizeInventory()];

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        int[] cont = {0, 0, 0};

        if(this.controller != null) {

            LogHelper.info("Writing " + this.controller);
            cont[0] = this.controller.getX();
            cont[1] = this.controller.getY();
            cont[2] = this.controller.getZ();

        }

        compound.setInteger("controllerX", cont[0]);
        compound.setInteger("controllerY", cont[1]);
        compound.setInteger("controllerZ", cont[2]);

        super.writeToNBT(compound);

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound){

        super.readFromNBT(compound);

        int[] coords = {0, 0, 0};
        coords[0] = compound.getInteger("controllerX");
        coords[1] = compound.getInteger("controllerY");
        coords[2] = compound.getInteger("controllerZ");
        LogHelper.info("Loading " + Arrays.toString(coords));
        this.controller = new BlockPos(coords[0], coords[1], coords[2]);
        LogHelper.info("Loading " + this.controller);

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

            maxXP = XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)) * this.getInventoryStackLimit();
            if (this.controller != null) {

                XPControllerTileEntity te = (XPControllerTileEntity) worldObj.getTileEntity(this.controller);
                xp = te.getTotalXP(te.getID());

                if (isSlotOccupied(1)) {

                    if (this.getStackInSlot(1).getItem() == Item.getItemFromBlock(ModBlocks.xpBlock)) {

                        te.addXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());
                        this.setInventorySlotContents(1, new ItemStack(ModBlocks.xpBlock, this.getStackInSlot(1).stackSize - 1));

                    }

                }
                if (te.getTotalXP(te.getID()) >= XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1))) {

                    if (isSlotOccupied(0)) {

                        if (this.getStackInSlot(0).stackSize < this.getInventoryStackLimit()) {

                            this.setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, this.getStackInSlot(0).stackSize + 1));
                            te.removeXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());

                        }

                    } else {

                        this.setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, 1));
                        te.removeXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());

                    }

                }

            }

        }
        catch (NullPointerException e){

            LogHelper.error("Error: NullPointerException in XPInterfaceTileEntity.updateChildren() !");

        }

    }
}
