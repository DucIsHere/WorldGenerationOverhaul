🧩 Code Explanation - WorldGenerationOverhaul

Tài liệu này giải thích các class chính trong dự án, mục đích và cách chúng hoạt động.

***Tài liệu này được giải ra vẫn có thể có sai sót và lỗi chính tả. Nếu phát hiện sẽ được sửa ngay, và đây là tài liệu tham khảo***

---

##1. **HyperWorldGen.java**

**Mục đích**:
Đây là entry point của mod, nơi đăng ký toàn bộ hệ thống worldgen, biome, feature, effect và tick handler.

**Code Snippet**:

```java
public class HyperWorldGen implements ModInitializer {

    public static final String MOD_ID = "hyperworldgen";

    @Override
    public void onInitialize() {
        System.out.println("Initializing HyperWorldGen...");

        ModEffects.registerModEffects();

        ModConfiguredFeatures.bootstrap();
        ModPlacedFeatures.bootstrap();

        ModBiomes.registerBiomes();
        FrostbiteHandler.register();

        ModWorldGen.generateWorldGen();

        HandledScreens.register(
            
        );

        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if(world instanceof ServerWorld serverWorld){
                ColdEnvironment.tick(serverWorld);
            }
        });
    }
}
```

**Giải thích**:
MOD_ID = "hyperworldgen" → định danh mod.
onInitialize() là entrypoint được gọi khi mod load:

**Đăng ký Effect**: ModEffects.registerModEffects()

**Đăng ký Worldgen Features**:
ModConfiguredFeatures.bootstrap() → chuẩn bị feature config
ModPlacedFeatures.bootstrap() → chuẩn bị placement rule
**Đăng ký Biomes**: ModBiomes.registerBiomes()

**Handler gameplay**: FrostbiteHandler.register() → xử lý cơ chế Frostbite

**WorldGen**: ModWorldGen.generateWorldGen() → khởi tạo cấu hình worldgen

**Screen**: đăng ký GUI 

**Mối liên kết**:
Kết nối tất cả hệ thống (biomes, features, effects, handlers) vào vòng đời của Fabric.
Là “cửa chính” load toàn bộ các module khác.

---

##2. **HyperWorldGenClient**

**Mục đích**:
Entry point phía client, để log thông tin và xử lý event client-side (nếu cần).

**Code Snippet**:

```java
public class HyperWorldGenClient implements ClientModInitializer {

    public static final String MOD_ID = "hyperworldgen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("HyperWorldGen Client initialized!");
        // Tương lai: đăng ký render layer, HUD overlay, particle...
    }
}
```

**Giải thích**:
Đây là entrypoint client của Fabric.
Khi game chạy client, log "HyperWorldGen Client initialized!" sẽ hiện trong console.
Class này hiện tại chưa đăng ký gì ngoài log, nhưng là chỗ để thêm:
HUD overlay (ví dụ RewardHudOverlay)
Particle effect
Render layer custom

---

##3. ModBiomes.java

**Mục đích**:
Quản lý và đăng ký các biome custom vào hệ thống Minecraft Registry.

**Code Snippet**:

```java
package com.ducishere.hyperworldgen.world.biome;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static Biome BLIZZARD_HELL;

    public static void registerBiomes() {
        BLIZZARD_HELL = Registry.register(
                Registries.BIOME,
                new Identifier("hyperworldgen", "blizzard_hell"),
                BlizzardHellBiome.createBiome()
        );

        System.out.println("Biomes registered");
    }
}
```

**Giải thích**:
public static Biome BLIZZARD_HELL;
Khai báo một biến tĩnh để lưu biome tùy chỉnh.
registerBiomes()
Hàm này dùng để đăng ký tất cả biome custom vào Minecraft Registry.
Registry.register(...):
Registries.BIOME → loại registry là biome.
new Identifier("hyperworldgen", "blizzard_hell") → tên định danh: hyperworldgen:blizzard_hell.
BlizzardHellBiome.createBiome() → gọi hàm tạo biome từ class khác (BlizzardHellBiome).
Sau khi đăng ký, in ra "Biomes registered" trong console để debug.

**Mối liên kết**:
Liên kết với class BlizzardHellBiome.java → nơi định nghĩa chi tiết biome (climate, mob spawn, feature...).
Được gọi từ HyperWorldGen.onInitialize() để đảm bảo biome được load khi game khởi động.

---

##4. **ModSounds.java**

**Mục đích**:
Quản lý và đăng ký âm thanh tùy chỉnh (Sound Events) vào registry của Minecraft.

**Code Snippet**:

```java
package com.ducishere.hyperworldgen.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent FREE_LUCK_DISC;

    public static void registerSounds() {
        FREE_LUCK_DISC = Registry.register(
            Registries.SOUND_EVENT,
            new Identifier("hyperworldgen", "music_disc_free_luck"),
            new SoundEvent(new Identifier("hyperworldgen", "music_disc_free_luck"))
        );
        System.out.println("Sounds registered");
    }
}
```

**Giải thích**:

public static SoundEvent FREE_LUCK_DISC;
Khai báo một SoundEvent đại diện cho bản nhạc custom (music_disc_free_luck).
registerSounds()
Đăng ký FREE_LUCK_DISC vào Registries.SOUND_EVENT.
Sử dụng new Identifier("hyperworldgen", "music_disc_free_luck") → ID đầy đủ là hyperworldgen:music_disc_free_luck.
Sau khi đăng ký, in ra "Sounds registered" trong console.
Mối liên kết:
Sound này sẽ được gọi từ item music disc trong game.
File resources/assets/hyperworldgen/sounds.json cần định nghĩa sound để game nhận diện.
File .ogg thực tế phải nằm trong resources/assets/hyperworldgen/sounds/.

---

##5. **HyperChunkGenerator**

**Mục đích**:

Định nghĩa chunk generator tùy chỉnh cho thế giới HyperWorldGen, mở rộng giới hạn build height và áp dụng cơ chế noise riêng.

**Code Key Points** :

```java
public class HyperChunkGenerator extends ChunkGenerator {
    public static final Codec<HyperChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.INT.fieldOf("maxHeight").forGetter(cg -> cg.maxHeight),
        Codec.INT.fieldOf("minHeight").forGetter(cg -> cg.minHeight)
    ).apply(instance, HyperChunkGenerator::new));

    private final int maxHeight;
    private final int minHeight;

    public HyperChunkGenerator(int maxHeight, int minHeight) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
    }

    @Override
    public int getWorldHeight() { return 50000; }

    @Override
    public int getMinimumY() { return -25000; }

    @Override
    public int seaLevel() { return 75; }
}
```

**🔧 Chức năng chính**:

Codec
Cho phép serialize/deserialze thông tin generator trong JSON (dùng trong datapack/dimension json).
Các tham số: maxHeight, minHeight.
Thông số thế giới
getWorldHeight() → 50,000 block.
getMinimumY() → -25,000 block.
seaLevel() → 75.
Biome Population
Override populateBiomes(...) để gán biome cho từng chunk.
Sử dụng NoiseConfig và Blender để tính toán.
Carving (Hang động, khe núi)
carve(...): kiểm tra noise và thay block bằng AIR nếu thỏa điều kiện.
Áp dụng riêng cho GenerationStep.Carver.AIR.
Noise Population
populateNoise(...): dùng NoiseChunk để sinh block dựa trên cấu hình noise.
Chiều cao & cột block
getHeight(...): định nghĩa chiều cao cho WORLD_SURFACE (500) và OCEAN_FLOOR (-7500).
getColumnSample(...): trả về một VerticalBlockSample gồm STONE, DIRT, GRASS, WATER, AIR theo y-level.
River Generation
Hàm generateRiver(...):
Sinh sông rộng 15–30 block.
Nếu gặp OCEAN, MOUNTAIN, VOLCANO → kết thúc sông.
Bờ sông: DIRT. Giữa: WATER.

**📌 Tóm tắt**
HyperChunkGenerator mở rộng hệ thống worldgen:
Thế giới cực cao: từ -25,000 → +25,000.
Carving + noise riêng biệt.
Sinh sông tùy chỉnh, có logic dừng khi gặp biome đặc biệt.

---

##6. **ModConfiguredFeatures.java**

**Mục đích**:
Định nghĩa ConfiguredFeature – mô tả cái gì sẽ spawn (block, patch, cấu trúc nhỏ).

**Code Spinet**:

```java
public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_RICE =
    registerKey("wild_rice");

public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
    context.register(WILD_RICE, new ConfiguredFeature<>(
        Feature.RANDOM_PATCH,
        new RandomPatchFeatureConfig(
            32, // số lần thử spawn mỗi chunk
            6,  // bán kính XZ
            2,  // bán kính Y
            () -> Feature.SIMPLE_BLOCK.configure(
                new SimpleBlockFeatureConfig(
                    BlockStateProvider.of(BlocksRegistry.WILD_RICE)
                )
            )
        )
    ));
}
```

**Giải thích**:
ConfiguredFeature = cái spawn (ví dụ: wild rice plant).
RandomPatchFeatureConfig = cơ chế random patch (32 tries / chunk, lan 6 block, cao 2 block).
BlockStateProvider = block được spawn.

---

##2. **ModPlacedFeatures.java**

**Mục đích**:
Định nghĩa PlacedFeature – mô tả spawn ở đâu, spawn bao nhiêu lần.

**Code Spinet**:

```java
public static final RegistryKey<PlacedFeature> WILD_RICE_PLACED =
    registerKey("wild_rice_placed");

public static void bootstrap(Registerable<PlacedFeature> context) {
    context.register(WILD_RICE_PLACED, new PlacedFeature(
        context.getHolderOrThrow(ModConfiguredFeatures.WILD_RICE),
        List.of(
            CountPlacementModifier.of(10),         // số lần spawn mỗi chunk
            InSquarePlacementModifier.spread(),    // trải đều trong chunk
            HeightmapPlacementModifier.of()        // dựa trên heightmap
        )
    ));
}
```

**Giải thích**:

ConfiguredFeature → cái spawn.
PlacedFeature → cách spawn (số lượng, vị trí, độ cao).
Có thể thêm filter (BiomeFilter) nếu chỉ muốn spawn ở biome cụ thể.

---

##3. **ModFeatures.java**

**Mục đích**:
Đăng ký Feature custom (Forge style).

**Code Spinet**:

```java
public static final RegistryObject<Feature<NoneFeatureConfiguration>> SNOW_LAYER_FEATURE =
    FEATURES.register("snow_layer_feature",
        () -> new SnowLayerFeature(NoneFeatureConfiguration.CODEC));
```

**Giải thích+**:
Feature = logic sinh custom (ví dụ: SnowLayerFeature).
PlacedFeature = feature + placement rule.
DeferredRegister (Forge) dùng để đăng ký vào registry.

**Mối liên kết**:
ModFeatures → định nghĩa Feature gốc.
ModConfiguredFeatures → định nghĩa Feature cụ thể (cấu hình block).
ModPlacedFeatures → đặt Feature đó vào thế giới với rule spawn.




Đây là tài liệu được viết lại, vẫn sẽ có sai sót xin thông cảm

Người viết**Wandering**

© 2025 DucIsHere.
