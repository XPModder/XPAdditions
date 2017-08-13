package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;


public class StarBlock extends Block {

    int radius = XPAdditions.instance.config.starBlockRadius.getInt();

    public StarBlock(String unlocalizedName) {

        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);
        this.setHardness(45.0F);
        this.setResistance(9000.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.0F);

    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {

        super.onBlockAdded(worldIn, pos, state);

        addLightsources(worldIn, pos);

    }

    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {

        RemoveLightsources(worldIn, pos);

    }

    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn){

        RemoveLightsources(worldIn, pos);

    }

    public void RemoveLightsources(World worldIn, BlockPos pos){

        int maxX = pos.getX() + radius + 2;
        int minX = pos.getX() - radius - 2;
        int maxY = pos.getY() + radius + 2;
        int minY = pos.getY() - radius - 2;
        int maxZ = pos.getZ() + radius + 2;
        int minZ = pos.getZ() - radius - 2;

        int currX;
        int currY;
        int currZ;

        BlockPos currPos;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++) {

                for (currZ = minZ; currZ <= maxZ; currZ++) {

                    currPos = new BlockPos(currX, currY, currZ);

                    if (worldIn.getBlockState(currPos).getBlock() == ModBlocks.brightStarBlock) {

                        worldIn.setBlockToAir(currPos);

                    }

                }

            }

        }
    }

    public void addLightsources(World worldIn, BlockPos pos) {

        int maxX = pos.getX() + radius + 2;
        int minX = pos.getX() - radius - 2;
        int maxY = pos.getY() + radius + 2;
        int minY = pos.getY() - radius - 2;
        int maxZ = pos.getZ() + radius + 2;
        int minZ = pos.getZ() - radius - 2;

        int currX;
        int currY;
        int currZ;

        Chunk chunk;

        BlockPos currPos;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++) {

                for (currZ = minZ; currZ <= maxZ; currZ++) {

                    currPos = new BlockPos(currX, currY, currZ);

                    if (MathHelper.isPosOnSphere(currPos, radius, pos) || MathHelper.isPosInSphere(currPos, radius, pos)) {

                        if (worldIn.isAirBlock(currPos)) {

                            chunk = worldIn.getChunkFromBlockCoords(currPos);

                            if (chunk.getLightFor(EnumSkyBlock.BLOCK, currPos) < 8) {

                                worldIn.setBlockState(currPos, ModBlocks.brightStarBlock.getDefaultState());

                            }

                        }

                    }

                }

            }

        }

    }

}
