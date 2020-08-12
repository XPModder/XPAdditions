package com.xpmodder.xpadditions.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class ModConfiguration {

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    static{

        COMMON_BUILDER.comment(ConfigCategories.WORLD.comment).push(ConfigCategories.WORLD.name);

        setupBlock(ConfigCategories.WORLD);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment(ConfigCategories.UTILITY.comment).push(ConfigCategories.UTILITY.name);

        setupBlock(ConfigCategories.UTILITY);

        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment(ConfigCategories.OTHER.comment).push(ConfigCategories.OTHER.name);

        setupBlock(ConfigCategories.OTHER);

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

    }

    private static void setupBlock(ConfigCategories category){

        switch(category){

            case WORLD:

                COMMON_BUILDER.comment("Ore Generation").push("oregen");

                ConfigBoolValues.GENERATE_OVERWORLD_ORE.currentValue = COMMON_BUILDER.comment(ConfigBoolValues.GENERATE_OVERWORLD_ORE.desc)
                        .define(ConfigBoolValues.GENERATE_OVERWORLD_ORE.name, ConfigBoolValues.GENERATE_OVERWORLD_ORE.defaultValue)
                        .get();
                ConfigBoolValues.GENERATE_NETHER_ORE.currentValue = COMMON_BUILDER.comment(ConfigBoolValues.GENERATE_NETHER_ORE.desc)
                        .define(ConfigBoolValues.GENERATE_NETHER_ORE.name, ConfigBoolValues.GENERATE_NETHER_ORE.defaultValue)
                        .get();
                ConfigBoolValues.GENERATE_END_ORE.currentValue = COMMON_BUILDER.comment(ConfigBoolValues.GENERATE_END_ORE.desc)
                        .define(ConfigBoolValues.GENERATE_END_ORE.name, ConfigBoolValues.GENERATE_END_ORE.defaultValue)
                        .get();
                ConfigIntValues.ORE_DENSITY.currentValue = COMMON_BUILDER.comment(ConfigIntValues.ORE_DENSITY.desc)
                        .defineInRange(ConfigIntValues.ORE_DENSITY.name, ConfigIntValues.ORE_DENSITY.defaultValue,
                                ConfigIntValues.ORE_DENSITY.min, ConfigIntValues.ORE_DENSITY.max)
                        .get();

                COMMON_BUILDER.pop();

                COMMON_BUILDER.comment("Meteorites").push("meteorites");

                ConfigBoolValues.GENERATE_METEORS.currentValue = COMMON_BUILDER.comment(ConfigBoolValues.GENERATE_METEORS.desc)
                        .define(ConfigBoolValues.GENERATE_METEORS.name, ConfigBoolValues.GENERATE_METEORS.defaultValue)
                        .get();
                ConfigIntValues.METEOR_DENSITY.currentValue = COMMON_BUILDER.comment(ConfigIntValues.METEOR_DENSITY.desc)
                        .defineInRange(ConfigIntValues.METEOR_DENSITY.name, ConfigIntValues.METEOR_DENSITY.defaultValue,
                                ConfigIntValues.METEOR_DENSITY.min, ConfigIntValues.METEOR_DENSITY.max)
                        .get();

                COMMON_BUILDER.pop();

                break;

            case UTILITY:

                COMMON_BUILDER.comment("Star Blocks").push("starblocks");

                ConfigIntValues.STAR_BLOCK_RADIUS.currentValue = COMMON_BUILDER.comment(ConfigIntValues.STAR_BLOCK_RADIUS.desc)
                        .defineInRange(ConfigIntValues.STAR_BLOCK_RADIUS.name, ConfigIntValues.STAR_BLOCK_RADIUS.defaultValue,
                                ConfigIntValues.STAR_BLOCK_RADIUS.min, ConfigIntValues.STAR_BLOCK_RADIUS.max)
                        .get();

                COMMON_BUILDER.pop();

                break;

            case OTHER:

                break;

        }

    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {

    }


}
