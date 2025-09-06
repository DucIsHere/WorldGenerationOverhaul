🧩 Code Explanation – WorldGenerationOverhaul
Tài liệu này giải thích các class chính trong dự án, mục đích và cách chúng hoạt động.

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



