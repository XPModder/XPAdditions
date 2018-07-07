package com.xpmodder.xpadditions;

import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.handler.ConfigurationHandler;
import com.xpmodder.xpadditions.init.MessageRegistry;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import com.xpmodder.xpadditions.proxy.CommonProxy;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, guiFactory = "com.xpmodder.xpadditions.config.GuiFactory")
public class XPAdditions {

    public static SimpleNetworkWrapper networkWrapper;

    @Mod.Instance(Reference.MOD_ID)
    public static XPAdditions instance;

    @SidedProxy(clientSide = Reference.ClientProxyClass, serverSide = Reference.ServerProxyClass)
    public static CommonProxy proxy;

    static{
        //For some reason, this has to be done here
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event){

        new ConfigurationHandler(event.getSuggestedConfigurationFile());

        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
        MessageRegistry.register(networkWrapper);

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
