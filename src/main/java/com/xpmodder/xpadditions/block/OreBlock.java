package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class OreBlock extends Block implements IMetaBlockName {

    private Item drop;
    private int meta;
    private int least_quantity;
    private int most_quantity;

    public OreBlock(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity) {
        super(mat);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(2.0f);
        this.setResistance(15.0f);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);
        this.drop = drop;
        this.meta = meta;
        this.least_quantity = least_quantity;
        this.most_quantity = most_quantity;
        this.setDefaultState(this.blockState.getBaseState().withProperty(DIMENSION, EnumDim.OVERWORLD));
    }

    public OreBlock(String unlocalizedName, Material mat, Item drop, int least_quantity, int most_quantity) {
        this(unlocalizedName, mat, drop, 0, least_quantity, most_quantity);
    }

    public OreBlock(String unlocalizedName, Material mat, Item drop) {
        this(unlocalizedName, mat, drop, 1, 1);
    }

    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
        return this.drop;
    }

    @Override
    public int damageDropped(IBlockState blockstate) {

        return this.meta;

    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, Random random) {

        if (this.least_quantity >= this.most_quantity)
            return this.least_quantity;
        return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);

    }

    public static final PropertyEnum DIMENSION = PropertyEnum.create("type", OreBlock.EnumDim.class);

    public enum EnumDim implements IStringSerializable {

        OVERWORLD(0, "overworld"),
        NETHER(1, "nether"),
        END(2, "end");

        private int ID;
        private String name;

        private EnumDim(int ID, String name) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { DIMENSION });
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        switch(meta){

            case 0:

                return getDefaultState().withProperty(DIMENSION, EnumDim.OVERWORLD);

            case 1:

                return getDefaultState().withProperty(DIMENSION, EnumDim.NETHER);

            case 2:

                return getDefaultState().withProperty(DIMENSION, EnumDim.END);

            default:

                return getDefaultState().withProperty(DIMENSION, EnumDim.OVERWORLD);

        }

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumDim type = (EnumDim) state.getValue(DIMENSION);
        return type.getID();
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(itemIn, 1, 0)); //Meta 0
        list.add(new ItemStack(itemIn, 1, 1)); //Meta 1
        list.add(new ItemStack(itemIn, 1, 2)); //Meta 2
    }

    @Override
    public String getSpecialName(ItemStack stack) {

        switch(stack.getItemDamage()){

            case 0:

                return "overworld";

            case 1:

                return "nether";

            case 2:

                return "end";

            default:

                return "overworld";

        }

    }



}
