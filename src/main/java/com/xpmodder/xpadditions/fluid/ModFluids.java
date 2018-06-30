package com.xpmodder.xpadditions.fluid;


import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

    public static fluidExp fluid_exp;
    public static BlockFluidXPA block_exp;

    public static void registerExp() {
        if (fluid_exp != null) {
            return;
        }
        fluid_exp = new fluidExp();
        FluidRegistry.registerFluid(fluid_exp);
        block_exp = new BlockFluidXPA(fluid_exp, Material.WATER);
        fluid_exp.setBlock(block_exp);
        FluidRegistry.addBucketForFluid(fluid_exp);
        block_exp.initModel();
    }

}
