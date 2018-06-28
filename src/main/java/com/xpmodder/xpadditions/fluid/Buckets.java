package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Buckets {

    public static ItemStack itemBucketLiquidXP;

    public static void createBuckets() {

        if (FluidRegistry.isUniversalBucketEnabled()) {

            FluidRegistry.addBucketForFluid(ModFluids.fluidLiquidXP);
            itemBucketLiquidXP = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, ModFluids.fluidLiquidXP);

        }

    }

    @SideOnly(Side.CLIENT)
    public static void registerRenderers() {



    }

}
