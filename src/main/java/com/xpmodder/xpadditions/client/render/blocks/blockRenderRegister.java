package com.xpmodder.xpadditions.client.render.blocks;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 0, new ModelResourceLocation("xpadditions:xp_ore_block_0"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 1, new ModelResourceLocation("xpadditions:xp_ore_block_1"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.xpOreBlock), 2, new ModelResourceLocation("xpadditions:xp_ore_block_2"));

    }

}
