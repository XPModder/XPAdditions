package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;


public class DropBlock extends Block {

    private Item drop;
    private int meta;
    private int least_quantity;
    private int most_quantity;

    public DropBlock(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity) {

        super(
                Block.Properties.create(mat)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(2.0F, 10.0F)
        );
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.drop = drop;
        this.meta = meta;
        this.least_quantity = least_quantity;
        this.most_quantity = most_quantity;

    }

    public DropBlock(String unlocalizedName, Material mat, Item drop) {
        this(unlocalizedName, mat, drop, 1, 1);
    }

    public DropBlock(String unlocalizedName, Material mat, Item drop, int least_quantity, int most_quantity) {
        this(unlocalizedName, mat, drop, 0, least_quantity, most_quantity);
    }

}
