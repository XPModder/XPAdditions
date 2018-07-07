package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import sun.rmi.runtime.Log;

import javax.annotation.Nullable;

public class XPControllerTileEntity extends TileEntity implements IInventory,ITickable{

    private ItemStack[] inventory;
    private static int count = 0;
    private int ID;
    private int storedXP;
    private String newName = "container.xp_controller_tile_entity";
    private int RSInt = 0;

    public XPControllerTileEntity(){

        this.inventory = new ItemStack[this.getSizeInventory()];
        for(int i = 0; i < this.inventory.length; i++){
            this.inventory[i] = ItemStack.EMPTY;
        }
        this.ID = count;
        this.storedXP = 0;
        count ++;

    }

    @Override
    public NBTTagCompound getUpdateTag(){

        return writeToNBT(new NBTTagCompound());

    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {

        readFromNBT(pkt.getNbtCompound());

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        compound.setInteger("ID", this.ID);
        compound.setInteger("storedXP", this.storedXP);
        compound.setInteger("RS", this.RSInt);
        LogHelper.info(this.RSInt);

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound){

        super.readFromNBT(compound);

        this.ID = compound.getInteger("ID");
        this.storedXP = compound.getInteger("storedXP");
        this.RSInt = compound.getInteger("RS");

    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new SPacketUpdateTileEntity(this.pos, -1, compound);

    }

    public int getID() {

        return this.ID;

    }

    public int getTotalXP(int id) {

        return this.storedXP;

    }

    public void setTotalXP(int totalXP, int id) {

        this.storedXP = totalXP;

    }

    public void setRS(int RSInt){

        this.RSInt = RSInt;
        this.markDirty();
        LogHelper.info("RS-Settings changed: " + this.RSInt);

    }

    public int getRS(){

        return this.RSInt;

    }

    public void addXP(int amount, int id){

        this.storedXP += amount;

    }

    public void removeXP(int amount, int id){

        this.storedXP -= amount;

    }

    @Override
    public int getSizeInventory() {

        return 1;

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

        return ItemStack.EMPTY;

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

        return 1;

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
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true;

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
    public void update() {

        if(!this.world.isRemote) {

            if (isSlotOccupied(0) && shouldRun()) {

                if (getStackInSlot(0).getItem() == Item.getItemFromBlock(ModBlocks.xpInterfaceBlock)) {

                    int[] coords = {this.pos.getX(), this.pos.getY(), this.pos.getZ()};
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setIntArray("controller", coords);
                    getStackInSlot(0).setTagCompound(compound);

                }

            }

        }

    }

    private boolean shouldRun(){

        if(this.RSInt == 0)
            return true;
        else if (this.RSInt == 1 && this.world.isBlockPowered(this.pos))
            return true;
        else if (this.RSInt == 2 && !this.world.isBlockPowered(this.pos))
            return true;
        else
            return false;

    }

}
