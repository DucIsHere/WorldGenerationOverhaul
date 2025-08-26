package com.ducishere.hyperworldgen.worldgen;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkRegion;
import net.minecraft.world.level.chunk.ChunkPos;

public class EntitySpawner {
  @Override
public void populateEntities(ChunkRegion region) {
    ChunkPos chunkPos = region.getCenterPos();
    var world = region.getWorld();

    for (int x = 0; x < 16; x++) {
        for (int z = 0; z < 16; z++) {
            int worldX = chunkPos.getStartX() + x;
            int worldZ = chunkPos.getStartZ() + z;

            // --- Surface spawn: Zombie ---
            int surfaceY = getHeight(worldX, worldZ, Heightmap.Type.WORLD_SURFACE, region, noiseConfig);
            BlockPos surfacePos = new BlockPos(worldX, surfaceY + 1, worldZ);

            var zombie = EntityType.ZOMBIE.create(world);
            if (zombie != null) {
                zombie.refreshPositionAndAngles(surfacePos, 0, 0);
                world.spawnEntity(zombie);
            }

            // --- Ocean floor spawn: Guardian ---
            int abyssY = -1500
            BlockPos oceanPos = new BlockPos(worldX, oceanY + 1, worldZ);

            var guardian = EntityType.GUARDIAN.create(world);
            if (guardian != null) {
                guardian.refreshPositionAndAngles(oceanPos, 0, 0);
                world.spawnEntity(guardian);
            }

            // --- Ocean abyss spawn: Elder Guardian y=-1500 ---
            int abyssY = -1500;
            BlockPos abyssPos = new BlockPos(worldX, abyssY + 1, worldZ);

            var elder = EntityType.ELDER_GUARDIAN.create(world);
            if (elder != null) {
                elder.refreshPositionAndAngles(abyssPos, 0, 0);
                world.spawnEntity(elder);
            }
        }
    }
}

}
