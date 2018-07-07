package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class BlockFluidXPA extends BlockFluidClassic {

    public static FluidStack stack;

    public BlockFluidXPA(Fluid fluid, Material material) {

        super(fluid, material);
        ModFluids.fluid_exp.setBlock(this);
        stack = new FluidStack(ModFluids.fluid_exp, Fluid.BUCKET_VOLUME);
        setCreativeTab(CreativeTabXPA.XPA_TAB);
        setUnlocalizedName("block_" + fluid.getName());
        setRegistryName("block_" + fluid.getName());

    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {

        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

        if(entityIn instanceof EntityPlayer){

            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 10));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 5));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1000, 5));

        }
        if(entityIn instanceof EntityLiving){

            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 10));
            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 5));
            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING));

        }

    }

    static {
        MinecraftForge.EVENT_BUS.register(BlockFluidXPA.class);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        Block block = ModFluids.block_exp;
        Item item = Item.getItemFromBlock(block);
        ModelBakery.registerItemVariants(item);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Reference.MOD_ID + ":fluid", stack.getFluid().getName());
        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
        ModelLoader.setCustomStateMapper(block, new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState bs) {
                return modelResourceLocation;
            }
        });
    }

}
