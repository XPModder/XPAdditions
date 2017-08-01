package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.item.BasicItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModItems {

    public static Item starItem;
    public static Item mediumStarItem;
    public static Item bigStarItem;
    public static Item denseStarItem;

    public static void createItems(){

        GameRegistry.registerItem(starItem = new BasicItem("star_item"), "star_item");
        GameRegistry.registerItem(mediumStarItem = new BasicItem("medium_star_item"),"medium_star_item");
        GameRegistry.registerItem(bigStarItem = new BasicItem("big_star_item"),"big_star_item");
        GameRegistry.registerItem(denseStarItem = new BasicItem("dense_star_item"),"dense_star_item");

    }

}
