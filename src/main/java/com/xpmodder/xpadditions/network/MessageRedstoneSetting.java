package com.xpmodder.xpadditions.network;

import com.xpmodder.xpadditions.tileentity.ModBaseTileEntity;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import com.xpmodder.xpadditions.utility.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MessageRedstoneSetting implements IMessage {

    private int toSend;
    private BlockPos pos;

    public MessageRedstoneSetting(){}

    public MessageRedstoneSetting(int toSend, BlockPos pos){
        this.toSend = toSend;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        pos = BlockPos.fromLong(buf.readLong());
        toSend = buf.readInt();
        LogHelper.info(buf);

    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeLong(this.pos.toLong());
        buf.writeInt(this.toSend);
        LogHelper.info(buf);

    }

    public static class MessageHandler implements IMessageHandler<MessageRedstoneSetting, IMessage>{


        @Override
        public IMessage onMessage(MessageRedstoneSetting message, MessageContext ctx) {

            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            World world = serverPlayer.getServerWorld();
            TileEntity te = world.getTileEntity(message.pos);

            if(world.isBlockLoaded(message.pos)){

                if(te instanceof XPControllerTileEntity)
                    serverPlayer.getServerWorld().addScheduledTask(() -> {
                        ((XPControllerTileEntity) te).setRS(message.toSend);
                    });
                else if(te instanceof ModBaseTileEntity)
                    serverPlayer.getServerWorld().addScheduledTask(() -> {
                        ((ModBaseTileEntity) te).setRS(message.toSend);
                    });

            }

            return null;
        }

    }

}