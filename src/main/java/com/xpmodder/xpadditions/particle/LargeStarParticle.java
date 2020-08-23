package com.xpmodder.xpadditions.particle;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.world.World;

import java.util.Random;

//TODO: create all the other classes and assets now required for particles!

public class LargeStarParticle extends Particle {

    public LargeStarParticle(World worldIn, double x, double y, double z, double velocityX, double velocityY, double velocityZ){

        super(worldIn, x, y, z, velocityX, velocityY, velocityZ);

        this.particleAlpha = 0.99F;

    }

    @Override
    public int getBrightnessForRender(float partialTick) {

        return 0xf000f0;

    }

    @Override
    public void tick(){

        Random ran = new Random();          //Random used for randomly changing particle size

        int num = ran.nextInt(20);   //This will be used to increase or decrease particle size. 20 -> inc/dec size by up to 0.2

        boolean grow = ran.nextBoolean();   //Determine if we want to grow or shrink the particle

        if(this.width < 0.5f){              //When the particle is already very small, make sure we grow it and do not shrink it any more
            grow = true;
        }
        else if(this.width > 2.5f){         //When the particle is very big, make sure we shrink it and don't grow it any more
            grow = false;
        }

        if(grow){                           //When we grow the particle, increase its width and height by (num / 100)
            setSize(this.width + (num / 100.0f), this.height + (num / 100.0f));
        }                                   //Otherwise decrease the width and height by (num / 100)
        else {
            setSize(this.width - (num / 100.0f), this.height - (num / 100.0f));
        }

        if (this.maxAge-- <= 0) {           //Finally decrease its max age so it will expire eventually
            this.setExpired();
        }

    }

    @Override
    public void renderParticle(BufferBuilder bufferBuilder, ActiveRenderInfo activeRenderInfo, float v, float v1, float v2, float v3, float v4, float v5) {

    }

    @Override
    public IParticleRenderType getRenderType() {
        return null;
    }

}
