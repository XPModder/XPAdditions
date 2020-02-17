package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.EnumRSMode;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;


public class XPInterfaceTileEntity extends ModBaseTileEntity implements IInventory,ITickable{

    private ItemStack[] inventory;
    public int xp = 0;
    public int maxXP;
    public int maxXPmB;


    public XPInterfaceTileEntity(){

        this.inventory = new ItemStack[this.getSizeInventory()];
        Arrays.fill(this.inventory, ItemStack.EMPTY);
        this.setCustomName("container.xp_interface_tile_entity");

    }

    @Override
    public int getSizeInventory() {

        return 5;

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

    public boolean isSlotOccupied(int index){

        try{

            if (this.getStackInSlot(index).getCount() > 0){

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
    public boolean isUsableByPlayer(EntityPlayer player) {

        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;

    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public NBTTagCompound childWriteNBT(NBTTagCompound compound){

        if(isSlotOccupied(0)){
            compound.setInteger("Slot0-Count", getStackInSlot(0).getCount());
        }
        else{
            compound.setInteger("Slot0-Count", 0);
        }
        if(isSlotOccupied(1)){
            compound.setInteger("Slot1-Count", getStackInSlot(1).getCount());
        }
        else{
            compound.setInteger("Slot1-Count", 0);
        }
        if(isSlotOccupied(2)){
            NBTTagCompound itemTags = new NBTTagCompound();
            getStackInSlot(2).writeToNBT(itemTags);
            compound.setTag("Slot2-Item", itemTags);
            compound.setInteger("Slot2-Count", getStackInSlot(2).getCount());
        }
        else{
            compound.setString("Slot2-Item", "null");
            compound.setInteger("Slot2-Count", 0);
        }
        if(isSlotOccupied(3)){
            NBTTagCompound itemTags = new NBTTagCompound();
            getStackInSlot(3).writeToNBT(itemTags);
            compound.setTag("Slot3-Item", itemTags);
            compound.setInteger("Slot3-Count", getStackInSlot(3).getCount());
        }
        else{
            compound.setString("Slot3-Item", "null");
            compound.setInteger("Slot3-Count", 0);
        }
        return compound;

    }

    @Override
    public void childReadNBT(NBTTagCompound compound){

        if(compound.getInteger("Slot0-Count") > 0)
            setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, compound.getInteger("Slot0-Count")));
        else
            setInventorySlotContents(0, ItemStack.EMPTY);

        if(compound.getInteger("Slot1-Count") > 0)
            setInventorySlotContents(1, new ItemStack(ModBlocks.xpBlock, compound.getInteger("Slot1-Count")));
        else
            setInventorySlotContents(1, ItemStack.EMPTY);

        if(compound.getInteger("Slot2-Count") > 0) {
            setInventorySlotContents(2, new ItemStack(compound.getCompoundTag("Slot2-Item")));
        }
        else
            setInventorySlotContents(2, ItemStack.EMPTY);

        if(compound.getInteger("Slot3-Count") > 0){
            setInventorySlotContents(3, new ItemStack(compound.getCompoundTag("Slot3-Item")));
        }
        else
            setInventorySlotContents(3, ItemStack.EMPTY);

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        switch(index){
            case 0:
                return false;

            case 1:
                return stack.getItem() == Item.getItemFromBlock(ModBlocks.xpBlock);

            case 2:
                return stack.getItem() == Items.BUCKET;

            case 3:

                return stack.getItem() == FluidUtil.getFilledBucket(new FluidStack(ModFluids.fluid_exp, Fluid.BUCKET_VOLUME)).getItem();

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
            this.setInventorySlotContents(i, ItemStack.EMPTY);

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

        //LogHelper.info(this.getName() + this.shouldRun());

        if(this.shouldRun()) {

            try {

                maxXP = XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)) * this.getInventoryStackLimit();
                maxXPmB = XPHelper.getXPfromMB(4000);

                XPControllerTileEntity te = (XPControllerTileEntity) world.getTileEntity(this.controller);
                xp = te.getTotalXP(te.getID());

                //Items

                if (isSlotOccupied(1)) {

                    if (this.getStackInSlot(1).getItem() == Item.getItemFromBlock(ModBlocks.xpBlock)) {

                        te.addXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());
                        this.setInventorySlotContents(1, new ItemStack(ModBlocks.xpBlock, this.getStackInSlot(1).getCount() - 1));

                    }

                }
                if (te.getTotalXP(te.getID()) >= XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1))) {

                    if (isSlotOccupied(0)) {

                        if (this.getStackInSlot(0).getCount() < this.getInventoryStackLimit()) {

                            this.setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, this.getStackInSlot(0).getCount() + 1));
                            te.removeXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());

                        }

                    } else {

                        this.setInventorySlotContents(0, new ItemStack(ModBlocks.xpBlock, 1));
                        te.removeXP(XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)), te.getID());

                    }

                }

                //Fluids

                if (isSlotOccupied(2)) {

                    if (getStackInSlot(2).getItem() == Items.BUCKET) {

                        if (te.getTotalXP(te.getID()) >= XPHelper.getXPfromMB(Fluid.BUCKET_VOLUME)) {

                            FluidActionResult result = FluidUtil.tryFillContainer(getStackInSlot(2), new FluidTank(ModFluids.fluid_exp, Fluid.BUCKET_VOLUME, Fluid.BUCKET_VOLUME), Fluid.BUCKET_VOLUME, te.getOwner(), true);
                            if( result.success)
                                te.removeXP(XPHelper.getXPfromMB(Fluid.BUCKET_VOLUME), te.getID());
                            setInventorySlotContents(2, result.result);

                        }

                    }

                }
                if (isSlotOccupied(3)) {

                    if(getStackInSlot(3).getItem() == FluidUtil.getFilledBucket(new FluidStack(ModFluids.fluid_exp, Fluid.BUCKET_VOLUME)).getItem()){
                        te.addXP(XPHelper.getXPfromMB(1000), te.getID());
                        setInventorySlotContents(3, new ItemStack(Items.BUCKET, 1));
                    }
                }

            } catch (NullPointerException e) {

                LogHelper.error("Error: NullPointerException in XPInterfaceTileEntity.updateChildren() ! Stacktrace: " + e.fillInStackTrace());

            }
        }

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

}
