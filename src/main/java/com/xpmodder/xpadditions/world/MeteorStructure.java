package com.xpmodder.xpadditions.world;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.utility.MathHelper;
import com.xpmodder.xpadditions.utility.ShapeHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class MeteorStructure extends WorldGenerator {


    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        createMeteor(worldIn, rand, createHole(worldIn, rand, position));

        return true;
    }

    public void createMeteor(World worldIn, Random rand, BlockPos position){

        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        ShapeHelper.genSphere(worldIn, x, y, z, 10, Blocks.AIR, true, Blocks.AIR);

        for (int cY = y; cY > 2; cY--){

            ShapeHelper.genCircle(worldIn, x, cY, z, 9, Blocks.LAVA, true, true, Blocks.LAVA);

        }

        int maxX = x + 8;
        int minX = maxX * -1;
        int maxY = y + 8;
        int minY = maxY * -1;
        int maxZ = z + 8;
        int minZ = maxZ * -1;

        int currX;
        int currY;
        int currZ;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++){

                for (currZ = minZ; currZ <= maxZ; currZ++){

                    if (MathHelper.isPosOnSphere(currX, currY, currZ, 6, x, y, z)){

                        if (rand.nextInt(100) < 5) {
                            worldIn.setBlockState(new BlockPos(currX, currY, currZ), ModBlocks.meteoriteStarBlock.getDefaultState());
                        }
                        else {
                            worldIn.setBlockState(new BlockPos(currX, currY, currZ), ModBlocks.meteoriteRockBlock.getDefaultState());
                        }

                    }
                    else if (MathHelper.isPosInSphere(currX, currY, currZ, 6, x, y, z)){

                        if (rand.nextInt(100) < 5) {
                            worldIn.setBlockState(new BlockPos(currX, currY, currZ), ModBlocks.meteoriteStarBlock.getDefaultState());
                        }
                        else {
                            worldIn.setBlockState(new BlockPos(currX, currY, currZ), ModBlocks.meteoriteRockBlock.getDefaultState());
                        }

                    }

                }

            }

        }

    }

    public BlockPos createHole(World worldIn, Random rand, BlockPos position){

        int diff = 255 - position.getY();
        int currX = position.getX() + diff;
        int currY;

        for (currY = 255; currY > 15; currY--){

            if (currY > position.getY()){

                ShapeHelper.genCircle(worldIn, currX, currY, position.getZ(), 10, Blocks.AIR,true, true, Blocks.AIR);

            }
            else if (currY <= position.getY()){

                ShapeHelper.genCircle(worldIn, currX, currY, position.getZ(), 10, ModBlocks.burntStoneBlock, true, true, Blocks.AIR );
                ShapeHelper.genCircle(worldIn, currX, currY + 1, position.getZ(), 10, Blocks.FIRE, false);

            }

            currX--;

        }

        currY -= 4;

        return new BlockPos(currX, currY, position.getZ());

    }

}
