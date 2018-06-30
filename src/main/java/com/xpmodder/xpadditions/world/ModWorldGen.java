package com.xpmodder.xpadditions.world;

import com.xpmodder.xpadditions.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch(world.provider.getDimension()){

            case 0:     //Overworld

                this.runGenerator(xp_ore_0_gen, world, random, chunkX, chunkZ, 2, 0, 40);



                if (random.nextInt(5000) == 1){

                    int randX = (chunkX * 16) + random.nextInt(16);
                    int randZ = (chunkZ * 16) + random.nextInt(16);

                    int y = getGroundFromAbove(world, randX, randZ);

                    genMeteor.generate(world, random, new BlockPos(randX, y, randZ));

                }

                break;

            case -1:    //Nether

                this.runGenerator(xp_ore_1_gen, world, random, chunkX, chunkZ, 10, 0, 255);
                break;

            case 1:     //End

                this.runGenerator(xp_ore_2_gen, world, random, chunkX, chunkZ, 30, 0, 255);
                break;


        }

    }

    private WorldGenerator xp_ore_0_gen;                        //Generates XP Ore in Overworld
    private WorldGenerator xp_ore_1_gen;                        //Generates Nether XP Ore in Nether
    private WorldGenerator xp_ore_2_gen;                        //Generates Ender XP Ore in End
    private WorldGenerator genMeteor = new MeteorStructure();   //Generates Meteors in Overworld


    public ModWorldGen() {

        this.xp_ore_0_gen = new WorldGenMinable(ModBlocks.xpOre0Block.getDefaultState(), 4);
        this.xp_ore_1_gen = new WorldGenMinable(ModBlocks.xpOre1Block.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK));
        this.xp_ore_2_gen = new WorldGenMinable(ModBlocks.xpOre2Block.getDefaultState(), 10, BlockMatcher.forBlock(Blocks.END_STONE));

    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

    public static int getGroundFromAbove(World world, int x, int z)
    {
        int y = 255;
        boolean foundGround = false;
        while(!foundGround && y-- >= 0)
        {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            // "ground" for our bush is grass or dirt
            foundGround = blockAt == Blocks.DIRT || blockAt == Blocks.GRASS || blockAt == Blocks.STONE || blockAt == Blocks.HARDENED_CLAY || blockAt == Blocks.SAND;
        }

        return y;
    }

}
