package com.xpmodder.xpadditions.creativetab;


import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class CreativeTabXPA {

    public static final ItemGroup XPA_TAB = (new ItemGroup(Reference.NAME) {

        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.xpBlock);
        }

    }).setTabPath(Reference.MOD_ID);

}
