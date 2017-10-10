package com.xpmodder.xpadditions.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class ModBaseTileEntity extends TileEntity implements ITickable {

    protected boolean connected = false;
    protected BlockPos controller;

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

    @Override
    public void update() {

        updateChildren();

    }

    public abstract void updateChildren();

}
