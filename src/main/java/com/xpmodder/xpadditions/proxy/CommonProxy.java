package com.xpmodder.xpadditions.proxy;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.crafting.ModCrafting;
import com.xpmodder.xpadditions.handler.ModGUIHandler;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.init.ModItems;
import com.xpmodder.xpadditions.init.ModTileEntities;
import com.xpmodder.xpadditions.world.ModWorldGen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {



    }

    public void init(FMLInitializationEvent e) {

        ModCrafting.initCrafting();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(XPAdditions.instance, new ModGUIHandler());

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
