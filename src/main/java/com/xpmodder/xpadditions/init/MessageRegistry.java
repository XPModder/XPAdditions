package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MessageRegistry {

    public static void register(SimpleNetworkWrapper networkWrapper){
        int ID = 0;

        networkWrapper.registerMessage(MessageRedstoneSetting.MessageHandler.class, MessageRedstoneSetting.class, ID, Side.SERVER);

    }

}
