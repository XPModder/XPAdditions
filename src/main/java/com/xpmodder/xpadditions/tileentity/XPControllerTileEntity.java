package com.xpmodder.xpadditions.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class XPControllerTileEntity extends TileEntity {

    private static int count = 0;
    private int ID;
    private int storedXP;

    public XPControllerTileEntity(){

        this.ID = count;
        count ++;

    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        super.writeToNBT(compound);

        compound.setInteger("ID", this.ID);
        compound.setInteger("storedXP", this.storedXP);

        return compound;

    }

    @Override
    public void readFromNBT(NBTTagCompound compound){

        super.readFromNBT(compound);

        this.ID = compound.getInteger("ID");
        this.storedXP = compound.getInteger("storedXP");

    }

    public int getID() {

        return this.ID;

    }

    public int getTotalXP(int id) {

        return storedXP;

    }

    public void setTotalXP(int totalXP, int id) {

        this.storedXP = totalXP;

    }

    public void addXP(int amount, int id){

        storedXP += amount;

    }

    public void removeXP(int amount, int id){

        storedXP -= amount;

    }

}
