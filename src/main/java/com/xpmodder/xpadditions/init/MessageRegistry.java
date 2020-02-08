package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.network.MessageDestroyBlock;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MessageRegistry {

    public static void register(SimpleNetworkWrapper networkWrapper){

        networkWrapper.registerMessage(MessageRedstoneSetting.MessageHandler.class, MessageRedstoneSetting.class, MessageRedstoneSetting.ID, Side.SERVER);
        networkWrapper.registerMessage(MessageDestroyBlock.MessageHandler.class, MessageDestroyBlock.class, MessageDestroyBlock.ID, Side.SERVER);

    }

}
