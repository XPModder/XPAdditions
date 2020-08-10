package com.xpmodder.xpadditions.proxy;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.capabilities.ModCapabilities;
import com.xpmodder.xpadditions.crafting.ModCrafting;
import com.xpmodder.xpadditions.handler.ModGUIHandler;
import com.xpmodder.xpadditions.world.ModWorldGen;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


public class CommonProxy {

    public void preInit(FMLCommonSetupEvent e) {

        ModCapabilities.register();

    }

    public void init(FMLInitializationEvent e) {

        ModCrafting.initCrafting();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(XPAdditions.instance, new ModGUIHandler());

    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
