package com.xpmodder.xpadditions.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerProfessionStorage implements Capability.IStorage<IPlayerProfessionCapability> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IPlayerProfessionCapability> capability, IPlayerProfessionCapability instance, Direction side) {

        CompoundNBT compound = new CompoundNBT();
        compound.putInt("profession", instance.getProfession());
        compound.putInt("level", instance.getLevel());
        compound.putInt("lastNum", instance.getLastNum());

        return compound;

    }

    @Override
    public void readNBT(Capability<IPlayerProfessionCapability> capability, IPlayerProfessionCapability instance, Direction side, INBT nbt) {

        CompoundNBT compound = new CompoundNBT();
        instance.setProfession(compound.getInt("profession"));
        instance.setLevel(compound.getInt("level"));
        instance.setLastNum(compound.getInt("lastNum"));

    }

}
