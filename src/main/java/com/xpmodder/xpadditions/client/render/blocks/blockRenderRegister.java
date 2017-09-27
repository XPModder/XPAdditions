package com.xpmodder.xpadditions.client.render.blocks;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public final class blockRenderRegister {

    public static void registerBlockRenderer() {

        reg(ModBlocks.starfieldBlock);
        reg(ModBlocks.brightStarBlock);
        reg(ModBlocks.starBlock);
        reg(ModBlocks.xpBlock);
        reg(ModBlocks.xpOreBlock, 0, "xp_ore_block_0");
        reg(ModBlocks.xpOreBlock, 1, "xp_ore_block_1");
        reg(ModBlocks.xpOreBlock, 2, "xp_ore_block_2");
        reg(ModBlocks.burntStoneBlock);
        reg(ModBlocks.meteoriteCobbleBlock);
        reg(ModBlocks.meteoriteRockBlock);
        reg(ModBlocks.meteoriteStarBlock);
        reg(ModBlocks.xpInterfaceBlock);

    }

    public static void reg(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }

    public static void reg(Block block, int meta, String file) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID + ":" + file, "inventory"));
    }

    public static void preInit(){

        LogHelper.info("blockRenderRegister.preInit():");
        LogHelper.info(new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock).getRegistryName() + "_0", "overworld"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 0, new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock).getRegistryName() + "_0", "overworld"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 1, new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock).getRegistryName() + "_1", "nether"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 2, new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock).getRegistryName() + "_2", "end"));

    }

}
