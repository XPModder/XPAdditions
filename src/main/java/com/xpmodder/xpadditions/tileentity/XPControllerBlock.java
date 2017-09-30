package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XPControllerBlock extends BlockContainer {

    public XPControllerBlock(){

        super(Material.IRON);

    }

    public XPControllerBlock(String unlocalizedName){

        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return null;

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){

        return EnumBlockRenderType.MODEL;

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {

        XPInterfaceTileEntity te = (XPInterfaceTileEntity) world.getTileEntity(pos);
        super.breakBlock(world, pos, blockstate);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        if (stack.hasDisplayName()) {

            ((XPInterfaceTileEntity) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());

        }

    }

}
