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
        reg(ModBlocks.xpBlock);
        reg(ModBlocks.xpOre0Block, "xp_ore_0_block");
        reg(ModBlocks.xpOre1Block, "xp_ore_1_block");
        reg(ModBlocks.xpOre2Block, "xp_ore_2_block");
        reg(ModBlocks.burntStoneBlock);
        reg(ModBlocks.meteoriteCobbleBlock);
        reg(ModBlocks.meteoriteRockBlock);
        reg(ModBlocks.meteoriteStarBlock);
        reg(ModBlocks.xpInterfaceBlock);
        reg(ModBlocks.xpControllerBlock);
        reg(ModBlocks.xpPlayerConnectorBlock);

    }

    public static void reg(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void reg(Block block, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + file, "inventory"));
    }

    public static void preInit(){ }

}
