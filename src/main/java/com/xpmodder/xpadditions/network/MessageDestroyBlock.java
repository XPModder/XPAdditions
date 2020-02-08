package com.xpmodder.xpadditions.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDestroyBlock implements IMessage {

    private int toSend;
    private BlockPos blockPos;
    public static final int ID = 1;

    public MessageDestroyBlock(){}

    public MessageDestroyBlock(int toSend, BlockPos blockPos){
        this.toSend = toSend;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        blockPos = BlockPos.fromLong(buf.readLong());
        toSend = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeLong(this.blockPos.toLong());
        buf.writeInt(this.toSend);

    }

    public static class MessageHandler implements IMessageHandler<MessageDestroyBlock, IMessage>{

        @Override
        public IMessage onMessage(MessageDestroyBlock message, MessageContext ctx) {

            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            World serverWorld = serverPlayer.getServerWorld();
            BlockPos pos = message.blockPos;
            int destroy = message.toSend;

            if(destroy == 1){
                serverWorld.setBlockToAir(pos);
            }

            return null;
        }

    }

}
