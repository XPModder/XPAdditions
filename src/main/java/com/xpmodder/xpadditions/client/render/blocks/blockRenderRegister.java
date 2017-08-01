package com.xpmodder.xpadditions.client.render.blocks;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class blockRenderRegister {

    public static void registerBlockRenderer() {

        reg(ModBlocks.starfieldBlock);
        reg(ModBlocks.brightStarBlock);
        reg(ModBlocks.starBlock);

    }

    public static void reg(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

}
