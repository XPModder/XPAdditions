package com.xpmodder.xpadditions.item;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.item.Item;

public class BasicItem extends Item {

    public BasicItem(String UnlocalizedName){

        super(new Item.Properties().group(CreativeTabXPA.XPA_TAB).setNoRepair());

        this.setRegistryName(Reference.MOD_ID, UnlocalizedName);

    }

    public BasicItem(String UnlocalizedName, int maxStackSize){

        super(new Item.Properties().group(CreativeTabXPA.XPA_TAB).setNoRepair().maxStackSize(maxStackSize));

        this.setRegistryName(Reference.MOD_ID, UnlocalizedName);

    }

}
