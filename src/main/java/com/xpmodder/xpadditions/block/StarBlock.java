package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.config.ConfigIntValues;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ToolType;

import java.util.Random;


public class StarBlock extends Block {

    int radius = ConfigIntValues.STAR_BLOCK_RADIUS.getValue();

    public StarBlock(String unlocalizedName) {

        super(
                Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(45.0F, 9000.0F).harvestLevel(3)
                .harvestTool(ToolType.PICKAXE)
                .lightValue(0)
                .tickRandomly()
        );
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);

    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {

        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);

        addLightsource(worldIn, pos);

    }

    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {

        RemoveLightsources(worldIn.getWorld(), pos);

    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn){

        RemoveLightsources(worldIn, pos);

    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random rand)
    {
        addLightsource(worldIn, pos);
    }

    //When the block is right-clicked, add a new lightsource and remove a little XP from the player
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult hit)
    {
        if(playerIn.isCreative()){

            addLightsource(worldIn, pos);

        }
        else if(playerIn.experienceTotal > 5){

            addLightsource(worldIn, pos);
            playerIn.experienceTotal -= 5;

        }
        return false;
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

        boolean remove = true;

        BlockPos currPos;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++) {

                for (currZ = minZ; currZ <= maxZ; currZ++) {

                    currPos = new BlockPos(currX, currY, currZ);

                    if (worldIn.getBlockState(currPos).getBlock() == ModBlocks.brightStarBlock) {

                        if(remove) {

                            BrightStarBlock block = (BrightStarBlock) worldIn.getBlockState(currPos).getBlock();
                            block.toDespawn = true;                                                                     //Flag the Block for despawning

                        }

                    }

                }

            }

        }
    }

    public void addLightsource(World worldIn, BlockPos pos) {

        int maxX = pos.getX() + radius + 2;
        int minX = pos.getX() - radius - 2;
        int maxY = pos.getY() + radius + 2;
        int minY = pos.getY() - radius - 2;
        int maxZ = pos.getZ() + radius + 2;
        int minZ = pos.getZ() - radius - 2;

        int currX;
        int currY;
        int currZ;

        boolean place = true;

        Chunk chunk;

        BlockPos currPos;

        for (currX = minX; currX <= maxX; currX++){

            for (currY = minY; currY <= maxY; currY++) {

                for (currZ = minZ; currZ <= maxZ; currZ++) {

                    currPos = new BlockPos(currX, currY, currZ);

                    if (MathHelper.isPosOnSphere(currPos, radius, pos) || MathHelper.isPosInSphere(currPos, radius, pos)) {

                        if (worldIn.isAirBlock(currPos)) {

                            chunk = worldIn.getChunkAt(currPos);

                            if (chunk.getLightValue(currPos) < 8) {

                                if(place) {

                                    worldIn.setBlockState(currPos, ModBlocks.brightStarBlock.getDefaultState());
                                    ((BrightStarBlock) worldIn.getBlockState(currPos).getBlock()).setToDespawn(false);
                                    place = false;

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}
