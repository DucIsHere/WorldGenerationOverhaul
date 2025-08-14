package com.hyperworldgen.world.biome.desert;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.OverworldBiomes;
import net.minecraft.world.level.biome.Biome.BiomeBuilder;

import java.util.function.Consumer;

public class HyperWorldGenDesert {
    public static final ResourceKey<Biome> DRY_SPINES = register("dry_spines");
    public static final ResourceKey<Biome> CHARRED_HOLLOW = register("charred_hollow");
    public static final ResourceKey<Biome> ASHEN_LOWLANDS = register("ashen_lowlands");
    public static final ResourceKey<Biome> SCORCHTRAIL_DUNES = register("scorchtrail_dunes");
    public static final ResourceKey<Biome> BURNING_SHARDLANDS = register("burning_shardlands");
    public static final ResourceKey<Biome> BLISTERED_WASTES = register("blistered_wastes");
    public static final ResourceKey<Biome> CRACKED_BONEFIELD = register("cracked_bonefield");
    public static final ResourceKey<Biome> OBSIDIAN_RIDGES = register("obsidian_ridges");
    public static final ResourceKey<Biome> DRYROOT_VALLEY = register("dryroot_valley");
    public static final ResourceKey<Biome> TOMB_VALLEY = register("tomb_valley");
    // ... thêm tiếp tới DESERT_30 nếu muốn

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation("hypergenworld", desert));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(DRY_SPINES, dry_spines(1));
        context.register(CHARRED_HOLLOW, charred_hollow(2));
        context.register(ASHEN_LOWLANDS, ashen_lowlands(3));
        context.register(SCORCHTRAIL_DUNES, scorchtrail_dunes(4));
        context.register(BURNING_SHARDLANDS, burning_shardlands(5));
        context.register(BLISTERED_WASTES, blistered_wastes(6));
        context.register(CRACKED_BONEFIELD, cracked_bonefield(7));
        context.register(OBSIDIAN_RIDGES, obsidian_ridges(8));
        context.register(DRYROOT_VALLEY, dryroot_valley(9));
        context.register(TOMB_VALLEY, tomb_valley(10));
        // ... đăng ký tiếp nếu có thêm
    }

    private static Biome dry_spines(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build();

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build();

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
            .build();

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.DESERT)
            .temperature(2.5F + (index * 0.00F)) // Nhiệt độ tăng dần
            .downfall(0.0F)
            .specialEffects(effects)
            .mobSpawnSettings(spawns)
            .generationSettings(generation)
            .build();
    }

    private static Biome charred_hollow(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffect.Builder()
             .fogColor(0xE0CDA9)
             .waterColor(0xE3DDB4)
             .waterFogColor(0xD1C59A)
             .skyColor(0xFFD580)
             .build();

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
             .build()

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
             .build()

        return new Biome.BiomeBuilder()
             .precipitation(Biome.Precipitation.DESERT)
             .temperature(2.8f + (index * 0.00f))
             .downfall(0.0f)
             .specialEffects(effects)
             .MobSpawnSettings(spawns)
             .generationSettings(generation)
             .build();
    }

    private static Biome ashen_lowlands(int index) {
        BiomeSpecialEffects effects= new BiomeSpecialEffects.Builder()
             .fogColor(0xE0CDA9)
             .waterColor(0xE3DDB4)
             .waterFogColor(0xD1C59A)
             .skyColor(0xFFD580)
             .build()
            
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
             .build()

        MobSpawnSettings spawn = new MobSpawnSetting.Builder()
             .build()

        return new Biome.BiomeBuilder()
             .precipitation(Biome.Precipitation.DESERT)
             .temperature(3.5f + (index * 0.00f))
             .downfall(0.0f)
             .specialEffects(effects)
             .MobSpawnSettings(spawns)
             .generationSettings(generation)
             .build();        
    }
    
    private static Biome scorchtrail_dunes(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
             .fogColor(0xE0CDA9)
             .waterColor(0xE3DDB4)
             .waterFogColor(0xD1C59A)
             .skyColor(0xFFD590)
             .build()
            
        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
             .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
             .build()

        return new Biome.BiomeBuilder()
             .precipitation(Biome.Precipitation.DESERT)
             .temperature(3.0f + (index * 0.02f))
             .downfall(0.0f)
             .specialEffects(effects)
             .MobSpawnSettings(spawns)
             .generationSettings(generation)
             .build();
    }

    private static Biome burning_shardlands(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
             .fogColor(0xE0CDA9)
             .waterColor(0xE3DDB4)
             .waterFogColor(0xD1C59A)
             .skyColor(0xFFD580)
             .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
             .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
             .build()

        return new Biome.BiomeBuilder()
             .precipitation(Biome.Precipitation.DESERT)
             .temperature(4.5f + (index * 0.05f))
             .downfall(0.00f)
             .specialEffects(effects)
             .MobSpawnSettings(spawns)
             .generationSettings(generation)
             .build();
    }

    private static Biome blistered_wastes(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
            .build()

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.NONE)
            .temperature(2.0f + (index * 0.00f))
            .downfall(0.00f)
            .specialEffects(effects)
            .MobSpawnSettings(spawns)
            .generationSettings(generation)
            .build();
    }

    private static Biome cracked_bonefield(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build()

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
            .build()

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitaion.DESERT)
            .temperature(4.25f + (index * 0.02f))
            .downfall(0.00f)
            .specialEffects(effects)
            .MobSpawnSettings(spawn)
            .generationSettings(generation)
            .build();
    }

    private static Biome obsidian_ridges(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 5, 2, 3))

        return  new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitaion.DESERT)
            .temperature(4.25f + (index * 0.02f))
            .downfall(0.00f)
            .specialEffects(effects)
            .MobSpawnSettings(spawn)
            .generationSettings(generation)
            .build();
            
    }

    private static Biome dryroot_valley(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
            .build()

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitaion.DESERT)
            .temperature(4.25f + (index * 0.02f))
            .downfall(0.00f)
            .specialEffects(effects)
            .MobSpawnSettings(spawn)
            .generationSettings(generation)
            .build();

        
    }

    private static Biome tomb_valley(int index) {
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .fogColor(0xE0CDA9)
            .waterColor(0xE3DDB4)
            .waterFogColor(0xD1C59A)
            .skyColor(0xFFD580)
            .build()

        BiomeGenerationSettings generation = new BiomeGenerationSettings.Builder()
            .build()

        MobSpawnSettings spawn = new MobSpawnSettings.Builder()
            .build()

        return new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitaion.DESERT)
            .temperature(5.00f + (index * 0.02f))
            .downfall(0.00f)
            .specialEffects(effects)
            .MobSpawnSettings(spawn)
            .generationSettings(generation)
            .build();
    
}
