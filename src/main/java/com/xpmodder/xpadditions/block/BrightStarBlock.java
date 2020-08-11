package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.network.MessageDestroyBlock;
import com.xpmodder.xpadditions.particle.LargeStarParticle;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class BrightStarBlock extends Block{

    boolean toDespawn = false;

    public BrightStarBlock(String unlocalizedName){

        super(
                Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(45.0F, 9000.0F)
                .harvestLevel(3).harvestTool(ToolType.PICKAXE)
                .tickRandomly()
                .lightValue(15)
                .doesNotBlockMovement()
        );
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.toDespawn = false;

    }

    public void setToDespawn(boolean set){
        this.toDespawn = set;
    }


    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public void randomTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand){

        double dX = (double) pos.getX() + 0.5D;
        double dY = (double) pos.getY() + 0.5D;
        double dZ = (double) pos.getZ() + 0.5D;

        //TODO: Particle rework
        LargeStarParticle effect = new LargeStarParticle(worldIn, dX, dY, dZ, 0.0D, 0.0D, 0.0D);
        //worldIn.addParticle(effect, dX, dY, dZ, 0.0F, 0.0F, 0.0F);

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
