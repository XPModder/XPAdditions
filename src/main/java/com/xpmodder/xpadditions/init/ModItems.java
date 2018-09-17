package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.item.BasicItem;
import com.xpmodder.xpadditions.item.BookItem;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

public final class ModItems {

    public static final Item starItem = new BasicItem("star_item");
    public static final Item mediumStarItem = new BasicItem("medium_star_item");
    public static final Item bigStarItem = new BasicItem("big_star_item");
    public static final Item denseStarItem = new BasicItem("dense_star_item");
    public static final Item xpOrbItem = new BasicItem("xp_orb_item");
    public static final Item bookItem = new BookItem("book_item");

    private static void initialiseItems(){

    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class RegistrationHandler {
        public static final Set<Item> ITEMS = new HashSet<>();

        /**
         * Register this mod's {@link Item}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final Item[] items = {
                    starItem,
                    mediumStarItem,
                    bigStarItem,
                    denseStarItem,
                    xpOrbItem,
                    bookItem
            };

            final IForgeRegistry<Item> registry = event.getRegistry();

            for (final Item item : items) {
                registry.register(item);
                ITEMS.add(item);
            }

            initialiseItems();
        }
    }

}
