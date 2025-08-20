package com.ducishere.hyperworldgen.init;

import com.ducishere.hyperworldgen.event.client.RegisterColorsEvent;
import com.ducishere.hyperworldgen.event.client.RegisterParticleSpritesEvent;
import com.ducishere.hyoerworldgen.util.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import com.ducishere.hyperworldgen.api.particle.TANParticles;
import com.ducishere.hyperworldgen.client.particle.ThermoregulatorParticle;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ClientSpriteRegistryCallback;
import net.minecraft.client.renderer.texture.SpriteAtlasTexture;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderLayer;


import static com.ducishere.hyperworldgen.api.block.TANBlocks.RAIN_COLLECTOR;
import static com.ducishere.hyperworldgen.api.block.TANBlocks.WATER_PURIFIER;

public class ModClient
{
    public static void registerBlockColors(RegisterColorsEvent.Block event)
    {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x47DAFF, RAIN_COLLECTOR);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x3F76E4, WATER_PURIFIER);
    }

    public static void registerParticleSprites(RegisterParticleSpritesEvent event)
    ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEX).register((atlas, registry) -> {
        registry.register(TANParticles.THERMOREGULATOR_COOL);
        registry.register(TANParticles.THERMOREGULATOR_WARM);
        registry.register(TANParticles.THERMOREGULATOR_NEUTRAL);
    });

    // Particle factory
    ParticleFactoryRegistry.getInstance().register(TANParticles.THERMOREGULATOR_COOL, ThermoregulatorParticle.Provider::new);
    ParticleFactoryRegistry.getInstance().register(TANParticles.THERMOREGULATOR_WARM, ThermoregulatorParticle.Provider::new);
    ParticleFactoryRegistry.getInstance().register(TANParticles.THERMOREGULATOR_NEUTRAL, ThermoregulatorParticle.Provider::new);

    public static void setupRenderTypes()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(RAIN_COLLECTOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WATER_PURIFIER, RenderLayer.getCutout());
    }
}
