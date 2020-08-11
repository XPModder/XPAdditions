package com.xpmodder.xpadditions.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemBlockMeta extends BlockItem {

    public ItemBlockMeta(Block block) {
        super(block, new Item.Properties().maxDamage(0).setNoRepair());
        if (!(block instanceof IMetaBlockName)) {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of ISpecialBlockName!", block.getRegistryName()));
        }

    }

    public int getMetadata(int damage)
    {
        return damage;
    }

}
