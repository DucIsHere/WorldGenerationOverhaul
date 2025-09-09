📘 Các Noise đã dùng

**Noise**

## 🔹 1️⃣ Base terrain noise (cho tất cả biome)

-OpenSimplexBackend → hills, plains, general terrain

-RidgedBackend → mountains, peaks, volcano

-FastNoiseBackend → micro bumps, small hills, minor terrain

-TerraceBackend → rice terrace / tea field / vegetable field

-DomainWarpBackend → canyon, waterfall delta, terrain warp

-TerraBlendBackend → blend tất cả noise base → smooth terrain

-HybridBackend → blend tất cả backend flexible

## 🔹 2️⃣ Feature-specific noise

-CaveBackend → underground cave system

-RiverBackend → surface + underground river

-VolcanoBackend → volcano cone / crater

-WaterfallBackend → delta / cliff waterfall

-CellularBackend → patches / mesa / mushroom / micro hills / lava pockets

-MicroHillsBackend → small hills, bumps, terrain micro details

-MesaPatchBackend → mesa plateau / patch

-MushroomPatchBackend → mushroom biome distribution

-GlacierPatchBackend → snow / ice patches

-LavaPocketBackend → underground lava pockets

-SandPatchBackend → desert / beach micro detail

## 🔹 3️⃣ Biome + noise mapping (modular)

-Rice Tropical / Tea Field / Rice Field / Vegetable Farmer → TerraceBackend + TerraBlend + OpenSimplex

-Jungle → OpenSimplex + FastNoise + TerraBlend

-Extreme Hills / Mountain / Everest / Volcano → Ridged + DomainWarp + TerraBlend + Hybrid

-Desert / Mesa → Ridged + Cellular + TerraBlend

-Plains → OpenSimplex + FastNoise + TerraBlend

-Mushroom → FastNoise + Cellular

-Beach → OpenSimplex + TerraBlend

-Snow → Ridged + OpenSimplex + TerraBlend

-Mỗi biome có thể sử dụng từ 5–50 noise, scale height / depth extreme ±50,000

🔹 4️⃣ **Cave + River system**

-Underground cave → CaveBackend + HybridBackend

-River in cave → CaveBackend + RiverBackend, carve dynamic paths

-Surface river → RiverBackend + DomainWarpBackend → waterfall delta

-Waterfall → RiverBackend + WaterfallBackend → delta slope > threshold
