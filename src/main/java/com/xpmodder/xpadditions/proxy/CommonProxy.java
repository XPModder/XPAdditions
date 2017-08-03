package com.xpmodder.xpadditions.proxy;

import com.xpmodder.xpadditions.crafting.ModCrafting;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.init.ModItems;
import com.xpmodder.xpadditions.world.ModWorldGen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

        ModItems.createItems();
        ModBlocks.createBlocks();

    }

    public void init(FMLInitializationEvent e) {

        ModCrafting.initCrafting();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
