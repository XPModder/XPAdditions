package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockFluidXPA extends BlockFluidClassic {

    protected final Fluid fluid;

    public BlockFluidXPA(Fluid fluid, Material material) {

        super(fluid, material);
        this.fluid = fluid;

        setNames(fluid);

    }

    public static BlockFluidXPA create(Fluid fluid, Material material){

        BlockFluidXPA res;

        res = new BlockFluidXPA(fluid, material);

        res.init();

        fluid.setBlock(res);

        return res;

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

        if(this.isSourceBlock(worldIn, pos)){

            if(entityIn instanceof EntityPlayer){

                XPHelper.setPlayerXP((EntityPlayer) entityIn, ((EntityPlayer) entityIn).experienceLevel + 2);
                worldIn.setBlockToAir(pos);

            }

        }

    }

    static {
        MinecraftForge.EVENT_BUS.register(BlockFluidXPA.class);
    }

    protected void setNames(Fluid fluid) {
        setUnlocalizedName(fluid.getUnlocalizedName());
        setRegistryName("block_" + fluid.getName());
    }

    protected void init() {
        GameRegistry.register(this);
    }


}
