package com.xpmodder.xpadditions.block;

import static com.xpmodder.xpadditions.handler.ConfigurationHandler.starBlockRadius;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.init.ModBlocks;
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

        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());

        if (worldIn.getBlockState(pos2).getBlock() == ModBlocks.brightStarBlock){

            worldIn.setBlockToAir(pos2);

            for(int x = (radius * -1) - 1; x < radius; x++ ){

                for (int z = (radius * -1) - 1; z < radius; z++ ){

                    for (int y = 0; y < radius; y ++){

                        BlockPos currentPos = new BlockPos(pos2.getX() + x, pos2.getY() - y, pos2.getZ() + z);

                        if (worldIn.getBlockState(currentPos).getBlock() == ModBlocks.brightStarBlock){

                            worldIn.setBlockToAir(currentPos);

                        }

                    }

                }

            }

        }
        else if (worldIn.getBlockState(pos3).getBlock() == ModBlocks.brightStarBlock){

            worldIn.setBlockToAir(pos3);

            for(int x = (radius * -1) - 1; x < radius; x++ ){

                for (int z = (radius * -1) - 1; z < radius; z++ ){

                    for (int y = 0; y < radius; y ++){

                        BlockPos currentPos = new BlockPos(pos3.getX() + x, pos3.getY() + y, pos3.getZ() + z);

                        if (worldIn.getBlockState(currentPos).getBlock() == ModBlocks.brightStarBlock){

                            worldIn.setBlockToAir(currentPos);

                        }

                    }

                }

            }

        }

    }

    public void addLightsources(World worldIn, BlockPos pos){

        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockPos pos3 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());

        if ( worldIn.isAirBlock(pos2)){

            worldIn.setBlockState(pos2, ModBlocks.brightStarBlock.getDefaultState(), 2);

            for(int x = (radius * -1) - 1; x < radius; x+= 4 ){

                for (int z = (radius * -1) - 1; z < radius; z+= 4 ){

                    for (int y = 0; y < radius; y += 9){

                        BlockPos currentPos = new BlockPos(pos2.getX() + x, pos2.getY() - y, pos2.getZ() + z);

                        if (worldIn.isAirBlock(currentPos)){

                            worldIn.setBlockState(currentPos, ModBlocks.brightStarBlock.getDefaultState(), 2);

                        }

                    }

                }

            }

        }
        else if (worldIn.isAirBlock(pos3)){

            worldIn.setBlockState(pos3, ModBlocks.brightStarBlock.getDefaultState(), 2);

            for(int x = (radius * -1) - 1; x < radius; x+= 4 ){

                for (int z = (radius * -1) - 1; z < radius; z+= 4 ){

                    for (int y = 0; y < radius; y += 9){

                        BlockPos currentPos = new BlockPos(pos3.getX() + x, pos3.getY() + y, pos3.getZ() + z);

                        if (worldIn.isAirBlock(currentPos)){

                            worldIn.setBlockState(currentPos, ModBlocks.brightStarBlock.getDefaultState(), 2);

                        }

                    }

                }

            }

        }

    }

}
