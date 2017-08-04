package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

    public static Block starfieldBlock;
    public static Block brightStarBlock;
    public static Block starBlock;
    public static Block xpBlock;
    public static Block xpOreBlock;
    public static Block burntStoneBlock;
    public static Block meteoriteCobbleBlock;
    public static Block meteoriteRockBlock;
    public static Block meteoriteStarBlock;

    public static void createBlocks() {

        GameRegistry.registerBlock(starfieldBlock = new BasicBlock("starfield_block"),"starfield_block");
        GameRegistry.registerBlock(brightStarBlock = new BrightStarBlock("bright_star_block"), "bright_star_block");
        GameRegistry.registerBlock(starBlock = new StarBlock("star_block"), "star_block");
        GameRegistry.registerBlock(xpBlock = new BasicBlock("xp_block"), "xp_block");
        GameRegistry.registerBlock(xpOreBlock = new OreBlock( "xp_ore_block", Material.ROCK, ModItems.xpOrbItem, 5, 20), ItemBlockMeta.class, "xp_ore_block");
        GameRegistry.registerBlock(burntStoneBlock = new BasicBlock("burnt_stone_block"), "burnt_stone_block");
        GameRegistry.registerBlock(meteoriteCobbleBlock = new BasicBlock( "meteorite_cobble_block"), "meteorite_cobble_block");
        GameRegistry.registerBlock(meteoriteRockBlock = new DropBlock( "meteorite_rock_block", Material.ROCK, Item.getItemFromBlock(ModBlocks.meteoriteCobbleBlock)), "meteorite_rock_block");
        GameRegistry.registerBlock(meteoriteStarBlock = new DropBlock( "meteorite_star_block", Material.ROCK, ModItems.starItem, 1, 5), "meteorite_star_block");

    }

}
