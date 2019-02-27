package com.xpmodder.xpadditions.fluid;


import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.*;

public class ModFluids {

    public static fluidExp fluid_exp;
    public static BlockFluidXPA block_exp;

    public static void registerExp() {
        try {
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
        catch (Exception e){
            LogHelper.error("Exception occured in ModFluids.registerExp! Stacktrace: " + e.fillInStackTrace());
        }
    }

}
