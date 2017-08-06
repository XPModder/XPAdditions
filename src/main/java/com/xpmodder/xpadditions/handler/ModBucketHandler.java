package com.xpmodder.xpadditions.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class ModBucketHandler {

    public static ModBucketHandler instance = new ModBucketHandler();

    static {
        MinecraftForge.EVENT_BUS.register(instance);
    }

    private Map<BlockFluidClassic, Item> buckets = new HashMap<BlockFluidClassic, Item>();

    private ModBucketHandler() {
    }

    public void registerFluid(BlockFluidClassic fluidBlock, Item fullBucket) {
        buckets.put(fluidBlock, fullBucket);
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        // no instanceof check, someone may subclass the vanilla bucket. Also, this event comes in for emptying buckets that contain something.
        if (event != null && !event.isCanceled() && event.getTarget() != null && event.getTarget().typeOfHit == RayTraceResult.Type.BLOCK
                && event.getTarget().getBlockPos() != null && event.getFilledBucket() == null && event.getEntityPlayer() != null && event.getWorld() != null
                && event.getWorld().isBlockModifiable(event.getEntityPlayer(), event.getTarget().getBlockPos()) && event.getEmptyBucket() != null
                && event.getEmptyBucket().getItem() == Items.BUCKET && event.getEmptyBucket().stackSize > 0 && event.getTarget().sideHit != null
                && event.getEntityPlayer().canPlayerEdit(event.getTarget().getBlockPos().offset(event.getTarget().sideHit), event.getTarget().sideHit,
                event.getEmptyBucket())
                ) {
            ItemStack res = getFilledBucket(event.getWorld(), event.getTarget());
            if (res != null) {
                event.setFilledBucket(res);
                event.setResult(Event.Result.ALLOW);
            }
        }
    }

    private ItemStack getFilledBucket(World world, RayTraceResult pos) {
        final BlockPos blockPos = pos.getBlockPos();
        final Block block = world.getBlockState(blockPos).getBlock();
        if (block instanceof BlockFluidClassic && buckets.containsKey(block) && ((BlockFluidClassic) block).isSourceBlock(world, blockPos)) {
            final Item bucket = buckets.get(block);
            if (bucket != null) {
                world.setBlockToAir(blockPos);
                return new ItemStack(bucket);
            }
        }
        return null;
    }

}
