package com.ducishere.hyperworldgen.world.gen;

import com.ducishere.hyperworldgen.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.RuleTest;
import net.minecraft.world.gen.feature.TagMatchRuleTest;
import net.minecraft.registry.tag.BlockTags;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> STEEL_ABYSS_ORE_KEY =
            RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier("hyperworldgen", "steel_abyss_ore"));

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        // RuleTest cho base stone
        RuleTest baseStone = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);

        List<OreFeatureConfig.Target> steelAbyssTargets = List.of(
                OreFeatureConfig.createTarget(baseStone, ModBlocks.STEEL_ABYSS_ORE.getDefaultState())
        );

        context.register(STEEL_ABYSS_ORE_KEY,
                new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(steelAbyssTargets, 2)));
    }
}
