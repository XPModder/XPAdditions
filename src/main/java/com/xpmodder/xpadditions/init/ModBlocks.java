package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.block.*;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerBlock;
import com.xpmodder.xpadditions.tileentity.XPInterfaceBlock;
import com.xpmodder.xpadditions.tileentity.XPPipeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
    public static BlockContainer xpInterfaceBlock;
    public static Block xpControllerBlock;
    public static Block xpPipe;

    public static void createBlocks() {

        GameRegistry.registerBlock(starfieldBlock = new BasicBlock("starfield_block"),"starfield_block");
        GameRegistry.registerBlock(brightStarBlock = new BrightStarBlock("bright_star_block"), "bright_star_block");
        GameRegistry.registerBlock(starBlock = new StarBlock("star_block"), "star_block");
        GameRegistry.registerBlock(xpBlock = new BasicBlock("xp_block"), "xp_block");
        GameRegistry.registerBlock(xpOreBlock = new OreBlock( "xp_ore_block", Material.ROCK, ModItems.xpOrbItem, 5, 20), ItemBlockMeta.class, "xp_ore_block");
        GameRegistry.registerBlock(burntStoneBlock = new BasicBlock("burnt_stone_block"), "burnt_stone_block");
        GameRegistry.registerBlock(meteoriteCobbleBlock = new BasicBlock( "meteorite_cobble_block"), "meteorite_cobble_block");
        GameRegistry.registerBlock(meteoriteRockBlock = new DropBlock( "meteorite_rock_block", Material.ROCK, Item.getItemFromBlock(ModBlocks.meteoriteCobbleBlock)), "meteorite_rock_block");
        GameRegistry.registerBlock(meteoriteStarBlock = new DropBlock( "meteorite_star_block", Material.ROCK, ModItems.starItem, 1, 3), "meteorite_star_block");
        GameRegistry.registerBlock(xpInterfaceBlock = new XPInterfaceBlock("xp_interface_block"), "xp_interface_block");
        GameRegistry.registerBlock(xpControllerBlock = new XPControllerBlock("xp_controller_block"), "xp_controller_block");
        GameRegistry.registerBlock(xpPipe = new XPPipeBlock("xp_pipe"), "xp_pipe");

    }

}
