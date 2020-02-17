package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.config.ConfigIntValues;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;


public class StarBlock extends Block {

    int radius = ConfigIntValues.STAR_BLOCK_RADIUS.getValue();

    public StarBlock(String unlocalizedName) {

        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);
        this.setHardness(45.0F);
        this.setResistance(9000.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.0F);
        this.setTickRandomly(true);

    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {

        super.onBlockAdded(worldIn, pos, state);

        addLightsource(worldIn, pos);

    }

    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {

        RemoveLightsources(worldIn, pos);

    }

    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn){

        RemoveLightsources(worldIn, pos);

    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        addLightsource(worldIn, pos);
    }

    //When the block is right-clicked, add a new lightsource and remove a little XP from the player
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
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

                            chunk = worldIn.getChunkFromBlockCoords(currPos);

                            if (chunk.getLightFor(EnumSkyBlock.BLOCK, currPos) < 8) {

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
