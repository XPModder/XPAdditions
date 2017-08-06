package com.xpmodder.xpadditions.block;

import static com.xpmodder.xpadditions.handler.ConfigurationHandler.starBlockRadius;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.MathHelper;
import com.xpmodder.xpadditions.utility.ShapeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class StarBlock extends Block {

    int radius = starBlockRadius;

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
        int minX = maxX * -1;
        int maxZ = pos.getZ() + radius + 2;
        int minZ = maxZ * -1;

        int currX;
        int currZ;

        for (currX = minX; currX <= maxX; currX++){

            for (currZ = minZ; currZ <= maxZ; currZ++){

                if (worldIn.getBlockState(new BlockPos(currX, pos.getY() + 1, currZ)).getBlock() == ModBlocks.brightStarBlock){

                    worldIn.setBlockState(new BlockPos(currX, pos.getY() + 1, currZ), Blocks.AIR.getDefaultState());

                }

            }

        }

    }

    public void addLightsources(World worldIn, BlockPos pos) {

        int maxX = pos.getX() + radius + 2;
        int minX = maxX * -1;
        int maxZ = pos.getZ() + radius + 2;
        int minZ = maxZ * -1;

        int currX;
        int currZ;

        int place = 0;

        for (currX = minX; currX <= maxX; currX++){

            for (currZ = minZ; currZ <= maxZ; currZ++){

                if (MathHelper.isPosOnHCircle(currX, pos.getY() + 1, currZ, radius, pos.getX(), pos.getY() + 1, pos.getZ()) || MathHelper.isPosInHCircle(currX, pos.getY(), currZ, radius, pos.getX(), pos.getY(), pos.getZ())){

                    if (worldIn.isAirBlock(new BlockPos(currX, pos.getY() + 1, currZ))){

                        if (worldIn.getLight(new BlockPos(currX, pos.getY() + 1, currZ)) < 8 || place == 6){

                            worldIn.setBlockState(new BlockPos(currX, pos.getY() + 1, currZ), ModBlocks.brightStarBlock.getDefaultState());
                            place = 0;

                        }

                    }

                }

                place++;

            }

        }

    }

}
