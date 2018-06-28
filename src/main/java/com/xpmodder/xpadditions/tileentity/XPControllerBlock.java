package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.handler.ModGUIHandler;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class XPControllerBlock extends BlockContainer {

    public XPControllerBlock(){

        super(Material.IRON);

    }

    public XPControllerBlock(String unlocalizedName){

        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);

    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new XPControllerTileEntity();

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){

        return EnumBlockRenderType.MODEL;

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {

        XPControllerTileEntity te = (XPControllerTileEntity) world.getTileEntity(pos);
        super.breakBlock(world, pos, blockstate);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {



    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {

            playerIn.openGui(XPAdditions.instance, ModGUIHandler.XP_Controller_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());

        }
        return true;

    }

}
