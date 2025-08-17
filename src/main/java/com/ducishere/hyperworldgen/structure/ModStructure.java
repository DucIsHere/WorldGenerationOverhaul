package com.ducishere.hyperworldgen.structure;

import com.ducishere.hyperworldgen.block.ColdAura;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.Structure;
import net.minecraft.util.Identifier;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.BiomeKeys;

public class ModStructures {

    public static Structure SOUL_LANTERN_STRUCTURE;

    public static void registerStructures() {
        SOUL_LANTERN_STRUCTURE = Registry.register(
            Registries.STRUCTURE,
            new Identifier("hyperworldgen", "soul_lantern_structure"),
            new Structure() // load từ soul_lantern_structure.nbt
        );
    }

    // Gọi khi generate chunk
    public static void trySpawnSoulLantern(ServerWorld world, BlockPos pos, Random random){
        // Kiểm tra biome snowy / icy
        var biome = world.getBiome(pos).value();
        if(biome.getKey().get() == BiomeKeys.SNOWY_TUNDRA || biome.getKey().get() == BiomeKeys.ICE_SPIKES){

            int chance = 100000; // 1 lantern / 100k chunks → cực hiếm
            if(random.nextInt(chance) == 0){
                SOUL_LANTERN_STRUCTURE.place(world, pos, null); // pseudo, thay bằng load NBT thật
                ColdAura.tick(world, pos); // Cold Aura active ngay
            }
        }
    }
}
