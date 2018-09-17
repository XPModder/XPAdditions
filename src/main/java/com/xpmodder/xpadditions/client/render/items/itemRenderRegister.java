package com.xpmodder.xpadditions.client.render.items;

import com.xpmodder.xpadditions.init.ModItems;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class itemRenderRegister {

    public static void registerItemRenderer() {

        reg(ModItems.starItem);
        reg(ModItems.mediumStarItem);
        reg(ModItems.bigStarItem);
        reg(ModItems.denseStarItem);
        reg(ModItems.xpOrbItem);
        reg(ModItems.bookItem);

    }

    public static void reg(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }

}
