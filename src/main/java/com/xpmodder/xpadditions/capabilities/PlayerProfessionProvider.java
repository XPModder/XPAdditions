package com.xpmodder.xpadditions.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerProfessionProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPlayerProfessionCapability.class)
    public static final Capability<IPlayerProfessionCapability> PROFESSION_CAP = null;

    private IPlayerProfessionCapability instance = PROFESSION_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == PROFESSION_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == PROFESSION_CAP ? PROFESSION_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return PROFESSION_CAP.getStorage().writeNBT(PROFESSION_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        PROFESSION_CAP.getStorage().readNBT(PROFESSION_CAP, this.instance, null, nbt);
    }

}
