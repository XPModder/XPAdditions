package com.xpmodder.xpadditions.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class XPPlayerConnectorTileEntity extends ModBaseTileEntity implements ITickable {

    public XPPlayerConnectorTileEntity() {

    }

    @Override
    public NBTTagCompound childWriteNBT(NBTTagCompound compound) {
        return compound;
    }

    @Override
    public void childReadNBT(NBTTagCompound compound) {

    }

    @Override
    public void updateChildren() {

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
