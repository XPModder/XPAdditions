package com.xpmodder.xpadditions.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Random;

public class LargeStarParticle extends Particle {

    public final ResourceLocation largeStarRL = new ResourceLocation("xpadditions:entity/largeStarParticle");

    public LargeStarParticle(World worldIn, double x, double y, double z, double velocityX, double velocityY, double velocityZ){

        super(worldIn, x, y, z, velocityX, velocityY, velocityZ);

        this.particleAlpha = 0.99F;

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(largeStarRL.toString());
        setParticleTexture(sprite);

    }

    @Override
    public int getFXLayer() {

        return 1;

    }

    @Override
    public int getBrightnessForRender(float partialTick) {

        return 0xf000f0;

    }

    @Override
    public boolean isTransparent() {

        return false;

    }

    @Override
    public void onUpdate(){

        Random ran = new Random();
        int num = ran.nextInt(20);

        if (particleScale > 2){
            particleScale = 1;
        }

        switch(num){

            case 1:
                particleScale = particleScale * (float)1.1;
                break;

            case 4:
                particleScale = particleScale * (float)1.2;
                break;

            case 8:
                particleScale = particleScale * (float)0.9;
                break;

            case 12:
                particleScale = particleScale * (float)0.8;
                break;

            case 16:
                particleScale = 1;
                break;

        }

        if (this.particleMaxAge-- <= 0) {
            this.setExpired();
        }

    }

}