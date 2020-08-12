package com.xpmodder.xpadditions.handler;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ParticleTextureHandler {

    public static TextureAtlasSprite starParticle;

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event)
    {
        ResourceLocation resource = new ResourceLocation(Reference.MOD_ID, "entity/large_star_particle");
        //TODO: fix particles
        //starParticle = event.addSprite(resource);

    }

}
