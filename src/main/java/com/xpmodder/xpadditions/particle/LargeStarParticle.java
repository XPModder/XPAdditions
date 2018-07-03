package com.xpmodder.xpadditions.particle;

import com.xpmodder.xpadditions.handler.ParticleTextureHandler;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Random;

public class LargeStarParticle extends Particle {

    //public final ResourceLocation largeStarRL = new ResourceLocation(Reference.MOD_ID, "entity/large_star_particle");

    public LargeStarParticle(World worldIn, double x, double y, double z, double velocityX, double velocityY, double velocityZ){

        super(worldIn, x, y, z, velocityX, velocityY, velocityZ);

        this.particleAlpha = 0.99F;
        setParticleTexture(ParticleTextureHandler.starParticle);

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
