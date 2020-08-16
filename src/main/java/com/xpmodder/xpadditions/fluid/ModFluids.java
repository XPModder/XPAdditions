package com.xpmodder.xpadditions.fluid;


import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;

public class ModFluids {

    public static final FlowingFluid FLOWING_EXP = (FlowingFluid)register("flowing_exp", new fluidExp.Flowing());
    public static final FlowingFluid STILL_EXP = (FlowingFluid)register("still_exp", new fluidExp.Source());

    private static <T extends Fluid> T register(String registryName, T fluid) {

        return (T) Registry.register(Registry.FLUID, registryName, fluid);

    }

    static {
        Iterator iterator = Registry.FLUID.iterator();

        while(iterator.hasNext()) {
            Fluid fluid = (Fluid)iterator.next();
            UnmodifiableIterator fluidIterator = fluid.getStateContainer().getValidStates().iterator();

            while(fluidIterator.hasNext()) {
                IFluidState fluidState = (IFluidState)fluidIterator.next();
                Fluid.STATE_REGISTRY.add(fluidState);
            }
        }

    }

}
