package com.xpmodder.xpadditions.utility;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShapeHelper {

    public static void genSphere(World worldIn, int x, int y, int z, int radius, Block outer, boolean fill, Block inner){

        int maxX = x + radius + 2;
        int minX = maxX * -1;
        int maxY = y + radius + 2;
        int minY = maxY * -1;
        int maxZ = z + radius + 2;
        int minZ = maxZ * -1;

        int currX;
        int currY;
        int currZ;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++){

                for (currZ = minZ; currZ <= maxZ; currZ++){

                    if (MathHelper.isPosOnSphere(currX, currY, currZ, radius, x, y, z)){

                        worldIn.setBlockState(new BlockPos(currX, currY, currZ), outer.getDefaultState());

                    }
                    else if (MathHelper.isPosInSphere(currX, currY, currZ, radius, x, y, z) && fill){

                        worldIn.setBlockState(new BlockPos(currX, currY, currZ), inner.getDefaultState());

                    }

                }

            }

        }

    }

    public static void genSphere(World worldIn, int x, int y, int z, int radius, Block outer){

        genSphere(worldIn, x, y, z, radius, outer, false, Blocks.AIR);

    }

    public static void genCircle(World worldIn, int x, int y, int z, int radius, Block ring, boolean replace,boolean fill, Block inner){

        int maxX = x + radius + 2;
        int minX = maxX * -1;
        int maxZ = z + radius + 2;
        int minZ = maxZ * -1;

        int currX;
        int currZ;

        for (currX = minX; currX <= maxX; currX++){

            for (currZ = minZ; currZ <= maxZ; currZ++){

                if (MathHelper.isPosOnHCircle(currX, y, currZ, radius, x, y, z)){

                    if (worldIn.getBlockState(new BlockPos(currX, y, currZ)).getBlock() != Blocks.AIR && replace == false){

                    }
                    else {

                        worldIn.setBlockState(new BlockPos(currX, y, currZ), ring.getDefaultState());

                    }

                }
                else if (MathHelper.isPosInHCircle(currX, y, currZ, radius, x, y, z) && fill){

                    if (worldIn.getBlockState(new BlockPos(currX, y, currZ)).getBlock() != Blocks.AIR && replace == false){

                    }
                    else {

                        worldIn.setBlockState(new BlockPos(currX, y, currZ), inner.getDefaultState());

                    }

                }

            }

        }

    }

    public static void genCircle(World worldIn, int x, int y, int z, int radius, Block ring, boolean replace){

        genCircle(worldIn, x, y, z, radius, ring, replace, false, Blocks.AIR);

    }


}
