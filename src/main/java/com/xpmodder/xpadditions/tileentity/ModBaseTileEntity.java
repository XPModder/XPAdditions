package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.utility.EnumRSMode;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class ModBaseTileEntity extends TileEntity implements ITickable {

    protected boolean connected = false;
    protected BlockPos controller;
    protected String newName = "container.xp_base_tile_entity";
    protected int RSInt = EnumRSMode.REDSTONE_IGNORED.getID();


    public void setRS(int RSInt){

        this.RSInt = RSInt;
        this.update();
        this.markDirty();

    }

    public int getRS(){

        return this.RSInt;

    }

    public boolean isConnected() {

        return connected;

    }

    public void setConnected(boolean connected) {

        this.connected = connected;

    }

    public BlockPos getController() {

        return controller;

    }

    public void setController(BlockPos controller) {

        this.controller = controller;

    }

    public String getCustomName() {

        return this.newName;

    }

    public void setCustomName(String customName) {

        this.newName = customName;

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

        int[] cont = {0, 0, 0};

        if(this.controller != null) {

            cont[0] = this.controller.getX();
            cont[1] = this.controller.getY();
            cont[2] = this.controller.getZ();

        }

        compound.setInteger("controllerX", cont[0]);
        compound.setInteger("controllerY", cont[1]);
        compound.setInteger("controllerZ", cont[2]);
        compound.setInteger("RS", this.RSInt);

        compound = childWriteNBT(compound);

        super.writeToNBT(compound);

        return compound;

    }

    public abstract NBTTagCompound childWriteNBT(NBTTagCompound compound);

    @Override
    public void readFromNBT(NBTTagCompound compound){

        super.readFromNBT(compound);

        int[] coords = {0, 0, 0};
        coords[0] = compound.getInteger("controllerX");
        coords[1] = compound.getInteger("controllerY");
        coords[2] = compound.getInteger("controllerZ");
        this.RSInt = compound.getInteger("RS");
        this.controller = new BlockPos(coords[0], coords[1], coords[2]);
        childReadNBT(compound);

    }

    public abstract void childReadNBT(NBTTagCompound compound);

    @Override
    public void update() {

        try {

            if (world.getTileEntity(this.controller) instanceof XPControllerTileEntity) {

                this.connected = true;

            } else {

                this.connected = false;

            }

        }
        catch (Exception e){

            this.connected = false;

        }

        updateChildren();

    }

    public abstract void updateChildren();

}
