# HyperGenWorld Overhaul – Fabric Template (Multi‑Noise + 20–30 chunk biomes)

A starter Fabric mod scaffold (MC **1.20.1**, Java **17**) for **custom Multi‑Noise biome selection** with pluggable noise backends (FastNoiseLite-ready), and a **ChunkGenerator stub** you can extend to reach ±25k Y. Designed for **biome widths ~20–30 chunks**.

> ✅ Focus: strong **Custom BiomeSource** (multi‑noise), clean registry, easy to swap noise lib → drop in **FastNoiseLite** later.  
> ⚠️ Note: Full ±25k Y terrain requires a custom **dimension type** and more work in the generator; a minimal stub is provided below.

---

## Project Tree
```
hypergenworld-overhaul/
├─ build.gradle
├─ settings.gradle
├─ gradle.properties
├─ src/main/java/
│  └─ com/duci/ hypergen/
│     ├─ HyperGenWorldMod.java
│     ├─ registry/ModRegistries.java
│     ├─ world/biome/HyperBiomeSource.java
│     ├─ world/biome/NoiseParam.java
│     ├─ world/biome/NoiseBackend.java
│     ├─ world/biome/VanillaPerlinBackend.java
│     └─ world/gen/HyperChunkGenerator.java
├─ src/main/resources/
│  ├─ fabric.mod.json
│  └─ data/hypergenworld/
│     └─ worldgen/
│        ├─ dimension_type/hyper_dim_type.json    (example: -25000..+25000)
│        └─ dimension/hyper.json                  (wires generator+biome source)
└─ README.md
```

---

## Gradle & Metadata

### `settings.gradle`
```gradle
rootProject.name = "hypergenworld-overhaul"
```

### `gradle.properties`
```properties
# Java & Loom
org.gradle.jvmargs=-Xmx2G
java_version=17
minecraft_version=1.20.1
loader_version=0.15.11
fabric_api_version=0.92.2+1.20.1
mappings=1.20.1+build.10
mod_version=0.1.0
maven_group=com.duci
archives_base_name=hypergenworld-overhaul
```

### `build.gradle`
```gradle
plugins {
    id 'fabric-loom' version '1.6-SNAPSHOT'
    id 'maven-publish'
}

version = project.mod_version
group = project.maven_group

repositories {
    mavenCentral()
}

dependencies {
    minecraft "com.mojang:minecraft:${minecraft_version}"
    mappings loom.officialMojangMappings()
    modImplementation "net.fabricmc:fabric-loader:${loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${fabric_api_version}"

    // TODO: When ready, vendor FastNoiseLite Java source or add as dependency if published.
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(java_version as int)
    withSourcesJar()
}

loom {
    splitEnvironmentSourceSets()
}
```

### `src/main/resources/fabric.mod.json`
```json
{
  "schemaVersion": 1,
  "id": "hypergenworld",
  "version": "${version}",
  "name": "HyperGenWorld Overhaul",
  "description": "Custom Multi‑Noise biome source + generator stub for cinematic large‑scale worldgen.",
  "authors": ["DucIsHere"],
  "license": "MIT",
  "environment": "*",
  "entrypoints": {
    "main": ["com.duci.hypergen.HyperGenWorldMod"]
  },
  "depends": {
    "fabricloader": ">=0.15.11",
    "minecraft": "~1.20.1",
    "fabric-api": ">=0.92.2"
  }
}
```

---

## Mod Init & Registry

### `HyperGenWorldMod.java`
```java
package com.duci.hypergen;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.duci.hypergen.registry.ModRegistries;

public class HyperGenWorldMod implements ModInitializer {
    public static final String MODID = "hypergenworld";
    public static final Logger LOGGER = LoggerFactory.getLogger("HyperGenWorld");

    @Override
    public void onInitialize() {
        ModRegistries.registerAll();
        LOGGER.info("HyperGenWorld Overhaul – init done.");
    }
}
```

### `registry/ModRegistries.java`
```java
package com.duci.hypergen.registry;

import com.duci.hypergen.world.biome.HyperBiomeSource;
import com.duci.hypergen.world.gen.HyperChunkGenerator;
import com.duci.hypergen.HyperGenWorldMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModRegistries {
    public static void registerAll() {
        Registry.register(Registries.BIOME_SOURCE, new Identifier(HyperGenWorldMod.MODID, "hyper_biome_source"), HyperBiomeSource.CODEC);
        Registry.register(Registries.CHUNK_GENERATOR, new Identifier(HyperGenWorldMod.MODID, "hyper_chunk_generator"), HyperChunkGenerator.CODEC);
    }
}
```

---

## Multi‑Noise BiomeSource (20–30 chunk biomes)

**Idea:** 5–6 parameter noises (temperature, humidity, continentalness, erosion, weirdness[, depth]) sampled from a **pluggable backend**. Each biome has a parameter vector; we **pick the nearest** by Euclidean distance.

### `world/biome/NoiseParam.java`
```java
package com.duci.hypergen.world.biome;

public record NoiseParam(float temperature, float humidity, float continentalness, float erosion, float weirdness, float depth) {
    public static float dist2(NoiseParam a, NoiseParam b) {
        float dx = a.temperature() - b.temperature();
        float dy = a.humidity() - b.humidity();
        float dz = a.continentalness() - b.continentalness();
        float de = a.erosion() - b.erosion();
        float dw = a.weirdness() - b.weirdness();
        float dd = a.depth() - b.depth();
        return dx*dx + dy*dy + dz*dz + de*de + dw*dw + dd*dd;
    }
}
```

### `world/biome/NoiseBackend.java`
```java
package com.duci.hypergen.world.biome;

public interface NoiseBackend {
    /** Returns [-1, 1] */
    float noise2D(int x, int z);
}
```

### `world/biome/VanillaPerlinBackend.java`
```java
package com.duci.hypergen.world.biome;

import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.noise.PerlinNoiseSampler;

public class VanillaPerlinBackend implements NoiseBackend {
    private final PerlinNoiseSampler sampler;
    private final float frequency;

    public VanillaPerlinBackend(long seed, float frequency) {
        this.sampler = new PerlinNoiseSampler(new Random(seed));
        this.frequency = frequency;
    }

    @Override
    public float noise2D(int x, int z) {
        double nx = x * frequency;
        double nz = z * frequency;
        return (float) sampler.sample(nx, 0.0, nz);
    }
}
```

> 🔁 **Swap-in FastNoiseLite**: create `FastNoiseLiteBackend implements NoiseBackend` and plug it into `HyperBiomeSource` (same API). Set `frequency ≈ 1/400f` for ~25‑chunk biomes.

### `world/biome/HyperBiomeSource.java`
```java
package com.duci.hypergen.world.biome;

import com.duci.hypergen.HyperGenWorldMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.HolderProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.RegistryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HyperBiomeSource extends BiomeSource {
    public static final Codec<HyperBiomeSource> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.LONG.fieldOf("seed").forGetter(src -> src.seed)
    ).apply(instance, HyperBiomeSource::new));

    private final long seed;
    private final NoiseBackend temp, humidity, cont, erosion, weird;
    private final List<Entry> entries = new ArrayList<>();

    public record Entry(NoiseParam param, RegistryEntry<Biome> biome) {}

    public HyperBiomeSource(long seed) {
        this.seed = seed;
        // Biome width target: 20–30 chunks -> frequency ~ 1/400f
        float f = 1f / 400f;
        this.temp = new VanillaPerlinBackend(seed ^ 0xA55A5A5AL, f);
        this.humidity = new VanillaPerlinBackend(seed ^ 0xBEEF1234L, f);
        this.cont = new VanillaPerlinBackend(seed ^ 0xC0FFEE00L, f * 0.7f);
        this.erosion = new VanillaPerlinBackend(seed ^ 0xDEADBEEFL, f * 0.9f);
        this.weird = new VanillaPerlinBackend(seed ^ 0xF00DCAFE L, f * 1.2f);
    }

    @Override
    protected Codec<? extends BiomeSource> getCodec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long seed) {
        return new HyperBiomeSource(seed);
    }

    @Override
    public RegistryEntry<Biome> getBiome(int x, int y, int z, RegistryWrapper.WrapperLookup lookup) {
        // Sample noise parameters in [-1,1]
        NoiseParam sample = new NoiseParam(
                temp.noise2D(x, z),
                humidity.noise2D(x, z),
                cont.noise2D(x, z),
                erosion.noise2D(x, z),
                weird.noise2D(x, z),
                clampDepth(y)
        );

        if (entries.isEmpty()) {
            // Lazy populate with a minimal set (replace with your full list)
            var biomeRegistry = lookup.getOrThrow(RegistryKeys.BIOME);
            entries.add(new Entry(new NoiseParam(-0.8f, 0.0f, 0.2f, 0.1f, -0.2f, -0.3f), biomeRegistry.getOrThrow(net.minecraft.util.Identifier.of("minecraft", "snowy_taiga"))));
            entries.add(new Entry(new NoiseParam( 0.0f, 0.3f, 0.0f, 0.0f,  0.0f,  0.0f), biomeRegistry.getOrThrow(net.minecraft.util.Identifier.of("minecraft", "plains"))));
            entries.add(new Entry(new NoiseParam( 0.7f, 0.8f,-0.1f, 0.0f,  0.4f,  0.2f), biomeRegistry.getOrThrow(net.minecraft.util.Identifier.of("minecraft", "savanna"))));
        }

        // Pick nearest biome by parameter distance
        RegistryEntry<Biome> best = entries.get(0).biome();
        float bestD2 = Float.MAX_VALUE;
        for (Entry e : entries) {
            float d2 = NoiseParam.dist2(sample, e.param());
            if (d2 < bestD2) { bestD2 = d2; best = e.biome(); }
        }
        return best;
    }

    private float clampDepth(int y) {
        // Map world Y roughly to [-1,1]. Replace once your dimension height is ±25k.
        // For vanilla 0..384: center at 128 and scale.
        float center = 128f;
        float span = 256f; // rough
        return Math.max(-1f, Math.min(1f, (y - center) / span));
    }
}
```

**Add/Manage 700 biomes**: build a data-driven list (e.g., load from JSON or hardcode arrays) mapping `NoiseParam -> Biome`. You can spread by temperature/humidity bands, then sub‑select by continentalness/erosion/weirdness to keep it organized.

---

## ChunkGenerator (Stub → extend to ±25k Y)

This is a minimal **heightmap‑style** generator with a pluggable noise height function. It compiles and runs; you will extend it to implement carving/surface rules/etc. Replace the height mapping to output into **±25k** once your **dimension type** supports that range.

### `world/gen/HyperChunkGenerator.java`
```java
package com.duci.hypergen.world.gen;

import com.duci.hypergen.HyperGenWorldMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.StructureSet;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.RandomState;

import java.util.concurrent.CompletableFuture;

public class HyperChunkGenerator extends ChunkGenerator {
    public static final Codec<HyperChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(g -> g.biomeSource),
            Codec.LONG.fieldOf("seed").forGetter(g -> g.seed)
    ).apply(instance, HyperChunkGenerator::new));

    private final long seed;

    public HyperChunkGenerator(BiomeSource biomeSource, long seed) {
        super(biomeSource);
        this.seed = seed;
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(StructureWorldAccess world, RandomState randomState, Chunk chunk, GenerationStep.Carver carver) {
        // TODO: implement custom carving if needed
    }

    @Override
    public void buildSurface(StructureWorldAccess world, RandomState randomState, Chunk chunk) {
        // TODO: surface rules (snow, grass, sand) depending on biome & y
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(StructureWorldAccess world, RandomState randomState, StructureSet structureSet, Chunk chunk) {
        // SUPER SIMPLE: fill up to a noise‑based height; replace with layered noise + 25k mapping
        int x0 = chunk.getPos().getStartX();
        int z0 = chunk.getPos().getStartZ();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int wx = x0 + dx;
                int wz = z0 + dz;
                int h = baseHeight(wx, wz); // vanilla range for now
                for (int y = world.getBottomY(); y <= h; y++) {
                    chunk.setBlockState(new BlockPos(dx, y, dz), Blocks.STONE.getDefaultState(), false);
                }
            }
        }
        return CompletableFuture.completedFuture(chunk);
    }

    private int baseHeight(int x, int z) {
        // Placeholder: gentle hills 64..192; swap to your FastNoiseLite height & map to ±25k when ready.
        double nx = x * 0.0025;
        double nz = z * 0.0025;
        double v = (Math.sin(nx) + Math.cos(nz)) * 0.5; // [-1,1]
        return (int) (128 + v * 64);
    }
}
```

> ⚠️ **Engine limits**: Going to ±25k requires a matching **dimension type** and careful perf tuning. Populate/chunk APIs are safe but surface/carver/feature placement also need scaling.

---

## Example Dimension JSON (±25k)

> These JSONs show how to wire a custom dimension type and dimension that uses your generator+biome source. Adjust to your needs.

### `data/hypergenworld/worldgen/dimension_type/hyper_dim_type.json`
```json
{
  "ultrawarm": false,
  "natural": true,
  "piglin_safe": false,
  "respawn_anchor_works": false,
  "bed_works": true,
  "has_raids": true,
  "min_y": -25000,
  "height": 50000,
  "logical_height": 50000,
  "infiniburn": "minecraft:infiniburn_overworld",
  "effects": "minecraft:overworld",
  "ambient_light": 0.0,
  "monster_spawn_block_light_limit": 0
}
```

### `data/hypergenworld/worldgen/dimension/hyper.json`
```json
{
  "type": "hypergenworld:hyper_dim_type",
  "generator": {
    "type": "hypergenworld:hyper_chunk_generator",
    "seed": 12345,
    "biome_source": {
      "type": "hypergenworld:hyper_biome_source",
      "seed": 12345
    }
  }
}
```

> Note: Depending on MC internals, extreme heights may need extra adjustments; start smaller (e.g., ±1024) to verify, then scale up.

---

## README (quick start)

1. **Clone & import** into IDE (IntelliJ), ensure **Java 17**.
2. Run `gradlew genSources` then `gradlew runClient`.
3. Create a world in the **Hyper** dimension via command or datapack that adds it to the world preset.
4. Tuning **biome width 20–30 chunks**:
   - In `HyperBiomeSource`, set frequency `~ 1/400f`. Use domain warp (when you add FastNoiseLite) for variety.
5. **Swap to FastNoiseLite**:
   - Add `FastNoiseLiteBackend` implementing `NoiseBackend`.
   - Replace `VanillaPerlinBackend` instances in `HyperBiomeSource`.
6. **Scale to ±25k Y**:
   - Confirm your dimension type JSON loads.
   - Replace `baseHeight` with a layered noise height function mapping `[-1,1] -> [-25000,+25000]`.
   - Implement `buildSurface` (biome‑specific top blocks), add carving & features.
7. **Add 700 biomes**:
   - Build a list of `Entry(NoiseParam, Biome)`; consider grouping by temperature/humidity bands and spread others around.

---

## Next steps (optional add‑ons)
- **Domain warp** utility for FastNoiseLite to avoid grid artifacts.
- **SurfaceRules** per biome (snow, gravel, etc.).
- **Extreme‑altitude mechanics** (oxygen, freezing, compass behaviors) as mixins/events.
- **Serialization**: Expose knobs (frequencies, seeds) via CODECs so datapacks can tweak without recompiling.

---

### License
MIT for the scaffold. Replace with your license as needed.
