package com.hypergenworld.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hypergenworld.registry.ModBiomes.BLIZZARD_HELL;

@Mod.EventBusSubscriber
public class ParticleBlizzardEffect {

    @SubscribeEvent
    public static void onRenderWorld(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        LocalPlayer player = mc.player;
        if (level == null || player == null) return;

        BlockPos pos = player.blockPosition();
        if (!level.getBiome(pos).is(BLIZZARD_HELL.getKey())) return;

        for (int i = 0; i < 80; i++) {
            double offsetX = (Math.random() - 0.5) * 20;
            double offsetZ = (Math.random() - 0.5) * 20;
            double x = player.getX() + offsetX;
            double y = player.getY() + Math.random() * 6 + 5;
            double z = player.getZ() + offsetZ;

            level.addParticle(ParticleTypes.SNOWFLAKE, x, y, z, 0, -0.1, 0);
        }
    }
}
