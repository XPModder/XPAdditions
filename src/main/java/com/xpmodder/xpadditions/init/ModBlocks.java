package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.block.BasicBlock;
import com.xpmodder.xpadditions.block.BrightStarBlock;
import com.xpmodder.xpadditions.block.StarBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

    public static Block starfieldBlock;
    public static Block brightStarBlock;
    public static Block starBlock;

    public static void createBlocks() {

        GameRegistry.registerBlock(starfieldBlock = new BasicBlock("starfield_block"),"starfield_block");
        GameRegistry.registerBlock(brightStarBlock = new BrightStarBlock("bright_star_block"), "bright_star_block");
        GameRegistry.registerBlock(starBlock = new StarBlock("star_block"), "star_block");

    }

}
