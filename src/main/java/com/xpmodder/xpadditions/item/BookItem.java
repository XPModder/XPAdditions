package com.xpmodder.xpadditions.item;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.handler.ModGUIHandler;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class BookItem extends BasicItem {

    public BookItem(String UnlocalizedName) {
        super(UnlocalizedName, 1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        playerIn.openGui(XPAdditions.instance, ModGUIHandler.Book_GUI, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);


        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
