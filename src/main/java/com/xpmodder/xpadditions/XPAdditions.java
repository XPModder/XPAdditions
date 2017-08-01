package com.xpmodder.xpadditions;

import com.xpmodder.xpadditions.handler.ConfigurationHandler;
import com.xpmodder.xpadditions.proxy.CommonProxy;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, guiFactory = "com.xpmodder.xpadditions.client.gui.GuiFactory")
public class XPAdditions {


    @Mod.Instance(Reference.MOD_ID)
    public static XPAdditions instance;

    @SidedProxy(clientSide = Reference.ClientProxyClass, serverSide = Reference.ServerProxyClass)
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event){

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        this.proxy.preInit(event);

        LogHelper.info("PreInitialization complete!");
    }

    @Mod.EventHandler
    public void Init (FMLInitializationEvent event){

        this.proxy.init(event);

        LogHelper.info("Initialization complete!");
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event){

        this.proxy.postInit(event);

        LogHelper.info("PostInitialization complete!");
    }

}
