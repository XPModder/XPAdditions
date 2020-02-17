package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.block.*;
import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerBlock;
import com.xpmodder.xpadditions.tileentity.XPInterfaceBlock;
import com.xpmodder.xpadditions.tileentity.XPPlayerConnectorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public final class ModBlocks {

    public static final Block starfieldBlock = new BasicBlock("starfield_block");
    public static final Block brightStarBlock = new BrightStarBlock("bright_star_block");
    public static final Block starBlock = new StarBlock("star_block");
    public static final Block xpBlock = new BasicBlock("xp_block");
    public static final Block xpOre0Block = new DropBlock("xp_ore_0_block", Material.ROCK, ModItems.xpOrbItem, 5, 20);
    public static final Block xpOre1Block = new DropBlock("xp_ore_1_block", Material.ROCK, ModItems.xpOrbItem, 5, 20);
    public static final Block xpOre2Block = new DropBlock("xp_ore_2_block", Material.ROCK, ModItems.xpOrbItem, 5, 20);
    public static final Block burntStoneBlock = new BasicBlock("burnt_stone_block");
    public static final Block meteoriteCobbleBlock = new BasicBlock( "meteorite_cobble_block");
    public static final Block meteoriteRockBlock = new DropBlock( "meteorite_rock_block", Material.ROCK, Item.getItemFromBlock(ModBlocks.meteoriteCobbleBlock));
    public static final Block meteoriteStarBlock = new DropBlock( "meteorite_star_block", Material.ROCK, ModItems.starItem, 1, 3);
    public static final BlockContainer xpInterfaceBlock = new XPInterfaceBlock("xp_interface_block");
    public static final Block xpControllerBlock = new XPControllerBlock("xp_controller_block");
    public static final Block xpPlayerConnectorBlock = new XPPlayerConnectorBlock("xp_player_connector_block");
    public static final Block baseCraftingBlock = new BasicBlock("base_crafting_block");

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class RegistrationHandler {
        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

        /**
         * Register this mod's {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();

            registry.registerAll(
                    starfieldBlock,
                    brightStarBlock,
                    starBlock,
                    xpBlock,
                    xpOre0Block,
                    xpOre1Block,
                    xpOre2Block,
                    burntStoneBlock,
                    meteoriteCobbleBlock,
                    meteoriteRockBlock,
                    meteoriteStarBlock,
                    xpInterfaceBlock,
                    xpControllerBlock,
                    xpPlayerConnectorBlock,
                    baseCraftingBlock,
                    ModFluids.block_exp
            );

        }


        /**
         * Register this mod's {@link ItemBlock}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final ItemBlock[] items = {
                    new ItemBlock(starfieldBlock),
                    new ItemBlock(brightStarBlock),
                    new ItemBlock(starBlock),
                    new ItemBlock(xpBlock),
                    new ItemBlock(xpOre0Block),
                    new ItemBlock(xpOre1Block),
                    new ItemBlock(xpOre2Block),
                    new ItemBlock(burntStoneBlock),
                    new ItemBlock(meteoriteCobbleBlock),
                    new ItemBlock(meteoriteRockBlock),
                    new ItemBlock(meteoriteStarBlock),
                    new ItemBlock(xpInterfaceBlock),
                    new ItemBlock(xpControllerBlock),
                    new ItemBlock(xpPlayerConnectorBlock),
                    new ItemBlock(baseCraftingBlock),
                    new ItemBlock(ModFluids.block_exp)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = com.google.common.base.Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }

            ModTileEntities.init();
        }
    }

}
