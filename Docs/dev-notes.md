
# 📓 dev-notes.md

## ✅ Current Progress
- [x] Setup **custom BiomeSource** (MultiNoise-based).
- [x] Setup **ChunkGenerator** with support for ±25,000 world height.
- [x] Add **FastNoiseLite backend** for noise sampling.
- [x] Provide base dimension JSON (hyper_overworld).

## 🚧 TODO
- [ ] Implement `pickBiome()` in **HyperBiomeSource** (map noise → biome).
- [ ] Add support for **700+ biomes** via registry.
- [ ] Implement **surface rules** (snow, sand, stone, etc.).
- [ ] Add **special biome mechanics**:
  - BlizzardHell → campfires extinguish, lava freezes to obsidian.
  - EverestSummit → oxygen debuff at Y > 18,000.
  - EchoingTundra → echo sound effect in caves.
- [ ] Optimize performance for large-scale terrain (25k vertical range).

## 📌 Notes on Adding Biomes
1. Register new biomes in `Registry<Biome>`.
2. Add them to **HyperBiomeSource** mapping logic:
   ```java
   if (temperature < -0.5 && erosion > 0.2) return BIOME_BLIZZARD_HELL;
   if (temperature > 0.5 && humidity < 0.0) return BIOME_DESERT;
   return BIOME_DEFAULT;
   ```
3. Define **SurfaceRules** for each biome:
   - BlizzardHell → snow/ice top layer.
   - Desert → sand/sandstone.
   - Forest → grass/dirt.

## ⚡ Performance Tips
- Use **low frequency noise** for biome placement (1f/400f ≈ 25 chunks per biome).
- Use **domain warp** for cinematic variation.
- Cache noise results where possible to avoid recomputation.

## 🛠️ Build / Test
```bash
./gradlew build
```
JAR will be located in `build/libs/`.
