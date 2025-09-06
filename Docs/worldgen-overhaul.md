
# HyperGenWorld - Worldgen Overhaul Base

Dự án **HyperGenWorld**: worldgen overhaul với độ cao cực lớn (±25,000 block), biome rộng 20–30 chunk, và hệ thống MultiNoise custom.

---

## 1. BiomeSource (HyperBiomeSource.java)

Custom BiomeSource dựa trên MultiNoise, sử dụng FastNoiseLite để tính toán tham số (temperature, humidity, erosion, continentalness, weirdness, depth).

```java
package com.ducishere.hypergenworld.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Custom MultiNoise BiomeSource cho HyperGenWorld
 * Sử dụng noise backend (vd: FastNoiseLite) để quyết định biome.
 */
public class HyperBiomeSource extends BiomeSource {
    public static final Codec<HyperBiomeSource> CODEC = Codec.unit(HyperBiomeSource::new);

    public HyperBiomeSource() {
        super(Stream.<Holder<Biome>>empty()); // sẽ override getNoiseBiome
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long seed) {
        return new HyperBiomeSource();
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, net.minecraft.world.level.biome.Climate.Sampler sampler) {
        // TODO: gọi FastNoiseLiteBackend để lấy temperature/humidity/erosion...
        // rồi map ra biome tương ứng.
        return null;
    }
}
```

---

## 2. ChunkGenerator (HyperChunkGenerator.java)

Generator tùy chỉnh, scale chiều cao [-25k..+25k].

```java
package com.ducishere.hypergenworld.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class HyperChunkGenerator extends ChunkGenerator {
    public static final Codec<HyperChunkGenerator> CODEC = Codec.unit(HyperChunkGenerator::new);

    public HyperChunkGenerator() {
        super(new HyperBiomeSource(), new HyperBiomeSource()); // placeholder
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new HyperChunkGenerator();
    }

    @Override
    public int getBaseHeight(int x, int z, net.minecraft.world.level.levelgen.Heightmap.Types type, LevelHeightAccessor world) {
        // TODO: dùng noise (FastNoiseLiteBackend) để scale height [-25000..25000]
        return 0;
    }
}
```

---

## 3. Noise Backend (FastNoiseLiteBackend.java)

Sử dụng FastNoiseLite để sample noise cho các tham số.

```java
package com.ducishere.hypergenworld.worldgen;

import com.fastnoiselite.FastNoiseLite;

public class FastNoiseLiteBackend {
    private final FastNoiseLite noise;

    public FastNoiseLiteBackend(long seed, float frequency) {
        noise = new FastNoiseLite((int) seed);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);
    }

    public float sample(int x, int y, int z) {
        return noise.GetNoise(x, z); // y có thể bỏ qua nếu chỉ muốn 2D biome
    }
}
```

---

## 4. Dimension Type (hyper_overworld.json)

```json
{
  "ultrawarm": false,
  "natural": true,
  "coordinate_scale": 1.0,
  "has_skylight": true,
  "has_ceiling": false,
  "ambient_light": 0.0,
  "fixed_time": null,
  "bed_works": true,
  "respawn_anchor_works": true,
  "min_y": -25000,
  "height": 50000,
  "logical_height": 50000,
  "infiniburn": "#minecraft:infiniburn_overworld",
  "effects": "minecraft:overworld",
  "piglin_safe": false,
  "has_raids": true,
  "monster_spawn_light_level": {
    "type": "minecraft:uniform",
    "value": [0, 7]
  },
  "monster_spawn_block_light_limit": 0
}
```

---

## 5. Dimension (hyper_overworld.json)

```json
{
  "type": "hypergenworld:hyper_chunk_generator",
  "generator": {
    "type": "hypergenworld:hyper_chunk_generator",
    "biome_source": {
      "type": "hypergenworld:hyper_biome_source"
    },
    "seed": 12345,
    "settings": "minecraft:overworld"
  }
}
```

---

## 6. Kết luận

- **Lib dùng:** FastNoiseLite.  
- **BiomeSource:** MultiNoise custom.  
- **ChunkGenerator:** scale chiều cao ±25k.  
- **Dimension JSON:** mở rộng thế giới.  

Tài liệu này là nền tảng để phát triển **HyperGenWorld Overhaul**.

**Tất cả các code trong này đều là tham khảo. Những đoạn code này đã được thay đổi ở tại các thư mục của bản mod. Hãy lưu ý khi tham khảo**•
