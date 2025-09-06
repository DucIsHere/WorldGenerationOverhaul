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



