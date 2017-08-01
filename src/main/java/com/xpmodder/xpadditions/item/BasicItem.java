package com.xpmodder.xpadditions.item;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import net.minecraft.item.Item;

public class BasicItem extends Item {

    public BasicItem(String UnlocalizedName){

        super();

        this.setUnlocalizedName(UnlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);

    }

}
