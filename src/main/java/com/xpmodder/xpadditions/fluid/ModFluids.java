package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModFluids {

    public static final String LIQUID_XP_NAME = "liquid_xp";


    public static Fluid fluidLiquidXP;
    public static BlockFluidXPA blockLiquidXP;


    public void registerFluids() {
        Fluid f = new Fluid(ModFluids.LIQUID_XP_NAME, getStill(ModFluids.LIQUID_XP_NAME), getFlowing(ModFluids.LIQUID_XP_NAME))
                .setDensity(30).setViscosity(10);
        FluidRegistry.registerFluid(f);
        fluidLiquidXP = FluidRegistry.getFluid(f.getName());
        blockLiquidXP = new BlockFluidXPA(fluidLiquidXP, Material.WATER);
    }

    public static ResourceLocation getStill(String fluidName) {
        return new ResourceLocation(Reference.MOD_ID, "fluids/" + fluidName + "_still");
    }

    public static ResourceLocation getFlowing(String fluidName) {
        return new ResourceLocation(Reference.MOD_ID, "fluids/" + fluidName + "_flow");
    }

    @SideOnly(Side.CLIENT)
    public void registerRenderers(){

        MinecraftForge.EVENT_BUS.register(this);
        registerFluidBlockRendering(fluidLiquidXP, "liquid_xp");

    }

    @SideOnly(Side.CLIENT)
    public void registerFluidBlockRendering(Fluid fluid, String name) {

        FluidStateMapper mapper = new FluidStateMapper(fluid);
        Block block = fluid.getBlock();
        Item item = Item.getItemFromBlock(block);

        // item-model
        if (item != null) {
            ModelLoader.registerItemVariants(item);
            ModelLoader.setCustomMeshDefinition(item, mapper);
        }
        // block-model
        if (block != null) {
            ModelLoader.setCustomStateMapper(block, mapper);
        }
    }

    public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

        public final Fluid fluid;
        public final ModelResourceLocation location;

        public FluidStateMapper(Fluid fluid) {
            this.fluid = fluid;
            location = new ModelResourceLocation(Reference.MOD_ID + ":fluids", fluid.getName());
        }

        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return location;
        }

        @Override
        public ModelResourceLocation getModelLocation(ItemStack stack) {
            return location;
        }
    }

}
