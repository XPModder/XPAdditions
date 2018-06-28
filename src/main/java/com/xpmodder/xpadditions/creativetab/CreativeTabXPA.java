package com.xpmodder.xpadditions.creativetab;


import com.xpmodder.xpadditions.init.ModItems;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabXPA {

    public static final CreativeTabs XPA_TAB = new CreativeTabs(Reference.MOD_ID) {

        @Override
        public ItemStack getTabIconItem() {

            return new ItemStack(ModItems.xpOrbItem);

        }

        @Override
        public String getTranslatedTabLabel(){

            return "XPAdditions";

        }

    };

}
