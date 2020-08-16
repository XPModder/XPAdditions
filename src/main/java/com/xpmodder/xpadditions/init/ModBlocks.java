package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.block.*;
import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerBlock;
import com.xpmodder.xpadditions.tileentity.XPInterfaceBlock;
import com.xpmodder.xpadditions.tileentity.XPPlayerConnectorBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
    public static final Block xpInterfaceBlock = new XPInterfaceBlock("xp_interface_block");
    public static final Block xpControllerBlock = new XPControllerBlock("xp_controller_block");
    public static final Block xpPlayerConnectorBlock = new XPPlayerConnectorBlock("xp_player_connector_block");
    public static final Block baseCraftingBlock = new BasicBlock("base_crafting_block");
    //TODO: figure out what the problem is and fix it!
    public static final Block fluidEXP = new FlowingFluidBlock(ModFluids.STILL_EXP, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class RegistrationHandler {
        public static final Set<BlockItem> ITEM_BLOCKS = new HashSet<>();

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
                    fluidEXP
            );

        }


        /**
         * Register this mod's {@link BlockItem}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

            final Item.Properties builder = new Item.Properties().maxStackSize(64).group(CreativeTabXPA.XPA_TAB).setNoRepair();
            final Item.Properties builder1max = new Item.Properties().maxStackSize(1).group(CreativeTabXPA.XPA_TAB).setNoRepair();

            final BlockItem[] items = {
                    new BlockItem(starfieldBlock, builder),
                    new BlockItem(brightStarBlock, builder),
                    new BlockItem(starBlock, builder),
                    new BlockItem(xpBlock, builder),
                    new BlockItem(xpOre0Block, builder),
                    new BlockItem(xpOre1Block, builder),
                    new BlockItem(xpOre2Block, builder),
                    new BlockItem(burntStoneBlock, builder),
                    new BlockItem(meteoriteCobbleBlock, builder),
                    new BlockItem(meteoriteRockBlock, builder),
                    new BlockItem(meteoriteStarBlock, builder),
                    new BlockItem(xpInterfaceBlock, builder),
                    new BlockItem(xpControllerBlock, builder),
                    new BlockItem(xpPlayerConnectorBlock, builder),
                    new BlockItem(baseCraftingBlock, builder),
                    new BlockItem(fluidEXP, builder1max)
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final BlockItem item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = com.google.common.base.Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }

            ModTileEntities.init();
        }
    }

}
