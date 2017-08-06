package com.xpmodder.xpadditions.fluid;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;

public class Buckets {

    public static ItemStack itemBucketLiquidXP;

    public static void createBuckets() {

        if(FluidRegistry.isUniversalBucketEnabled()){

            FluidRegistry.addBucketForFluid(ModFluids.fluidLiquidXP);
            itemBucketLiquidXP = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.fluidLiquidXP);

        }

    }

}
