package com.xpmodder.xpadditions.utility;

import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NetworkHelper {

    public boolean isConnected(BlockPos thisPos, World worldIn, BlockPos fromPos){

        int thisX = thisPos.getX();
        int thisY = thisPos.getY();
        int thisZ = thisPos.getZ();
        int fromX = fromPos.getX();
        int fromY = fromPos.getY();
        int fromZ = fromPos.getZ();

        if (fromX == thisX & fromY == thisY & fromZ != thisZ){

            if (fromZ < thisZ){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }
            else if (fromZ > thisZ){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }

        }
        else if (fromX == thisX & fromZ == thisZ & fromY != thisY){

            if (fromY < thisY){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }
            else if (fromY > thisY){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }

        }
        else if (fromZ == thisZ & fromY == thisY & fromX != thisX){

            if (fromX < thisX){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }
            else if (fromX > thisX){

                if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ - 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;
                else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                    return true;

            }

        }
        else if (fromX == thisX & fromY == thisY & fromZ == thisZ){

            if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ + 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                return true;
            else if ((worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY, thisZ - 1), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY, thisZ + 1)).getBlock() == ModBlocks.xpControllerBlock)
                return true;
            else if ((worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY + 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY + 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                return true;
            else if ((worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX, thisY - 1, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX, thisY - 1, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                return true;
            else if ((worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX + 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX + 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                return true;
            else if ((worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpPipe & isConnected(new BlockPos(thisX - 1, thisY, thisZ), worldIn, thisPos)) || worldIn.getBlockState(new BlockPos(thisX - 1, thisY, thisZ)).getBlock() == ModBlocks.xpControllerBlock)
                return true;

        }

        return false;

    }

    public boolean isConnected(BlockPos thisPos, World worldIn){

        return isConnected(thisPos, worldIn, thisPos);

    }

}
