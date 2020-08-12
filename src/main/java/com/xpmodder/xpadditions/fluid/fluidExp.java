package com.xpmodder.xpadditions.fluid;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public abstract class fluidExp extends FlowingFluid {

    public fluidExp() {
    }

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.FLOWING_EXP;
    }

    @Override
    public Fluid getStillFluid() {
        return ModFluids.STILL_EXP;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public Item getFilledBucket() {
        //TODO: create Bucket
        return null;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld iWorld, BlockPos blockPos, BlockState blockState) {

        TileEntity te = blockState.getBlock().hasTileEntity() ? iWorld.getTileEntity(blockPos) : null;
        Block.spawnDrops(blockState, iWorld.getWorld(), blockPos, te);

    }

    @Override
    protected int getSlopeFindDistance(IWorldReader iWorldReader) {
        return 4;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return null;
    }

    @Override
    public boolean isSource(IFluidState state) {

        if(state.getFluid() == getFlowingFluid()){
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader iWorldReader) {
        return 1;
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 7;
    }

    @Override
    protected boolean func_215665_a(IFluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
        return p_215665_5_ == Direction.DOWN && !p_215665_4_.isIn(FluidTags.WATER);
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public int getLevel(IFluidState p_207192_1_) {
        return 0;
    }

    public static class Flowing extends fluidExp {
        public Flowing() {
        }

        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> stateBuilder) {
            super.fillStateContainer(stateBuilder);
            stateBuilder.add(new IProperty[]{LEVEL_1_8});
        }

        public int getLevel(IFluidState fluidState) {
            return (Integer)fluidState.get(LEVEL_1_8);
        }

        public boolean isSource(IFluidState p_207193_1_) {
            return false;
        }
    }

    public static class Source extends fluidExp {
        public Source() {
        }

        public int getLevel(IFluidState fluidState) {
            return 8;
        }

        public boolean isSource(IFluidState fluidState) {
            return true;
        }
    }

}
