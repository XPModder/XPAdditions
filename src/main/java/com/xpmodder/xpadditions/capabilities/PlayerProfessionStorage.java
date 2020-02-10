package com.xpmodder.xpadditions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerProfessionStorage implements Capability.IStorage<IPlayerProfessionCapability> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IPlayerProfessionCapability> capability, IPlayerProfessionCapability instance, EnumFacing side) {

        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("profession", instance.getProfession());
        compound.setInteger("level", instance.getLevel());
        compound.setInteger("lastNum", instance.getLastNum());
        NBTTagCompound main = new NBTTagCompound();
        main.setTag("xpadditions", compound);
        return main;

    }

    @Override
    public void readNBT(Capability<IPlayerProfessionCapability> capability, IPlayerProfessionCapability instance, EnumFacing side, NBTBase nbt) {

        NBTTagCompound compound = (NBTTagCompound) ((NBTTagCompound)nbt).getTag("xpadditions");
        instance.setProfession(compound.getInteger("profession"));
        instance.setLevel(compound.getInteger("level"));
        instance.setLastNum(compound.getInteger("lastNum"));

    }

}
