package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.handler.ModGUIHandler;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class XPInterfaceBlock extends ContainerBlock {



    public XPInterfaceBlock() {

        super(Material.IRON);

    }

    public XPInterfaceBlock(String unlocalizedName){

        super(Material.IRON);
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);

    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {

        return new XPInterfaceTileEntity();

    }

    @Override
    public BlockRenderType getRenderType(BlockState state){

        return BlockRenderType.MODEL;

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {

        XPInterfaceTileEntity te = (XPInterfaceTileEntity) world.getTileEntity(pos);
        super.breakBlock(world, pos, blockstate);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        if (stack.hasDisplayName()) {

            ((XPInterfaceTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName().getString());

        }
        if (stack.hasTagCompound()){

            int[] coords = stack.getTagCompound().getIntArray("controller");
            ((XPInterfaceTileEntity) worldIn.getTileEntity(pos)).setController(new BlockPos(coords[0], coords[1], coords[2]));

        }

    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult hit) {

        if (!worldIn.isRemote) {

            playerIn.openGui(XPAdditions.instance, ModGUIHandler.XP_Interface_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());

        }
        return true;

    }

}
