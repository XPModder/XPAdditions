package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.network.MessageDestroyBlock;
import com.xpmodder.xpadditions.particle.LargeStarParticle;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BrightStarBlock extends Block{

    boolean toDespawn = false;

    public BrightStarBlock(String unlocalizedName){

        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.setHardness(45.0F);
        this.setResistance(9000.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(1.0F);
        this.setLightOpacity(0);
        this.setTickRandomly(true);
        this.toDespawn = false;

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

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public int tickRate(World worldIn) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand){

        double dX = (double) pos.getX() + 0.5D;
        double dY = (double) pos.getY() + 0.5D;
        double dZ = (double) pos.getZ() + 0.5D;

        LargeStarParticle effect = new LargeStarParticle(worldIn, dX, dY, dZ, 0.0D, 0.0D, 0.0D);
        Minecraft.getMinecraft().effectRenderer.addEffect(effect);

        //If this Block is flagged for despawning
        //This way we dont get a lag spike when we are removing the origin block.
        if (this.toDespawn) {
            int random = new Random().nextInt(10);
            if (random == 1) {                                                  //There is a 10% chance every tick the block receives for it to despawn.
                XPAdditions.networkWrapper.sendToServer(new MessageDestroyBlock(1, pos));
            }
        }


    }

}
