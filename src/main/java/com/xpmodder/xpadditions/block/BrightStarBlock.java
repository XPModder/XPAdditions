package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.particle.LargeStarParticle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BrightStarBlock extends Block{

    public BrightStarBlock(String unlocalizedName){

        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setHardness(45.0F);
        this.setResistance(9000.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(1.0F);
        this.setLightOpacity(0);
        this.setTickRandomly(true);

    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){

        double dX = (double)pos.getX() + 0.5D;
        double dY = (double)pos.getY() + 0.5D;
        double dZ = (double)pos.getZ() + 0.5D;


        LargeStarParticle effect = new LargeStarParticle(worldIn, dX, dY, dZ, 0.0D, 0.0D, 0.0D);
        Minecraft.getMinecraft().effectRenderer.addEffect(effect);

    }


}
