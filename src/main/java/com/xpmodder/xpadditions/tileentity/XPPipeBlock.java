package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XPPipeBlock extends BlockContainer {

    public XPPipeBlock(){

        super(Material.IRON);

    }

    public XPPipeBlock(String unlocalizedName){

        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new XPPipeTileEntity();

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){

        return EnumBlockRenderType.MODEL;

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {

        XPPipeTileEntity te = (XPPipeTileEntity) world.getTileEntity(pos);
        super.breakBlock(world, pos, blockstate);

    }

}
