package com.xpmodder.xpadditions;

import com.xpmodder.xpadditions.capabilities.ModCapabilities;
import com.xpmodder.xpadditions.config.ModConfiguration;
import com.xpmodder.xpadditions.handler.GeneralEventHandler;
import com.xpmodder.xpadditions.init.MessageRegistry;
import com.xpmodder.xpadditions.proxy.ClientProxy;
import com.xpmodder.xpadditions.proxy.CommonProxy;
import com.xpmodder.xpadditions.proxy.ServerProxy;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Proxy;


//@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, guiFactory = "com.xpmodder.xpadditions.config.GuiFactory")
@Mod(Reference.MOD_ID)
public class XPAdditions {

    public static SimpleChannel networkWrapper;

    public static Logger ModLogger = LogManager.getLogger();

    public static GeneralEventHandler generalEventHandler;

    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);


    public XPAdditions(){

        //TODO: Remove old Config Classes if no longer needed
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModConfiguration.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfiguration.COMMON_CONFIG);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);

        MinecraftForge.EVENT_BUS.register(generalEventHandler);

        MinecraftForge.EVENT_BUS.register(ModCapabilities.class);

        ModConfiguration.loadConfig(ModConfiguration.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MOD_ID + "-client.toml"));
        ModConfiguration.loadConfig(ModConfiguration.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.MOD_ID + "-common.toml"));

    }


    public void preInit (final FMLCommonSetupEvent event){

        generalEventHandler = new GeneralEventHandler();

        //Configuration needs complete Rewriting!
        //new ConfigurationHandler(event.getSuggestedConfigurationFile());

        networkWrapper = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID, "main"), ()-> Reference.PROTOCOL_VERSION, Reference.PROTOCOL_VERSION::equals, Reference.PROTOCOL_VERSION::equals);
        MessageRegistry.register(networkWrapper);

        this.proxy.preInit(event);

        LogHelper.info("PreInitialization complete!");
    }

    //Do Init Events need to be done in preInit as well now?
    //This doesnt work any longer
    /*
    @Mod.EventHandler
    public void Init (FMLInitializationEvent event){

        this.proxy.init(event);

        LogHelper.info("Initialization complete!");
    }
     */


}
