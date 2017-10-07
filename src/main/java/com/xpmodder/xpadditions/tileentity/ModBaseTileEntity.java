package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import scala.actors.threadpool.Arrays;

public abstract class ModBaseTileEntity extends TileEntity implements ITickable {

    protected boolean connected = false;
    protected BlockPos controller;
    protected int controllerID;
    protected Block[] XPBlocks = {ModBlocks.xpInterfaceBlock, ModBlocks.xpPipe};

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

    public void setControllerID(int ID){

        this.controllerID = ID;

    }

    public int getControllerID() {

        return controllerID;

    }

    @Override
    public void update() {

        if (worldObj.getBlockState(new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ())).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ());

        }
        else if (worldObj.getBlockState(new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ())).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ());

        }
        else if (worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ())).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ());

        }
        else if (worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ())).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ());

        }
        else if (worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1)).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1);

        }
        else if (worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1)).getBlock() == ModBlocks.xpControllerBlock){

            this.connected = true;
            this.controller = new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1);

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ())).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ()))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX() + 1, this.pos.getY(), this.pos.getZ()))).getController();

            }

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ())).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ()))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX() - 1, this.pos.getY(), this.pos.getZ()))).getController();

            }

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ())).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ()))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ()))).getController();

            }

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ())).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ()))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ()))).getController();

            }

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1)).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 1))).getController();

            }

        }
        else if (Arrays.asList(this.XPBlocks).contains( worldObj.getBlockState(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1)).getBlock())){

            if (((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1))).isConnected()) {

                this.connected = true;
                this.controller = ((ModBaseTileEntity) worldObj.getTileEntity(new BlockPos(this.pos.getX(), this.pos.getY(), this.pos.getZ() - 1))).getController();

            }

        }
        else{

            this.connected = false;

        }

        if (connected)
            updateChildren();

    }

    public abstract void updateChildren();

}
