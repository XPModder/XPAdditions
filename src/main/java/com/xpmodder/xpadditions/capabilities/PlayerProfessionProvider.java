package com.xpmodder.xpadditions.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerProfessionProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IPlayerProfessionCapability.class)
    public static final Capability<IPlayerProfessionCapability> PROFESSION_CAP = null;

    private LazyOptional<IPlayerProfessionCapability> instance = LazyOptional.of(PROFESSION_CAP::getDefaultInstance);

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction side) {
        return capability == PROFESSION_CAP ? instance.cast() : null;
    }

    @Override
    public INBT serializeNBT() {
        return PROFESSION_CAP.getStorage().writeNBT(PROFESSION_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        PROFESSION_CAP.getStorage().readNBT(PROFESSION_CAP, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }

}
