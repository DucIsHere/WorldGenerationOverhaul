
# HyperGenWorld

**HyperGenWorld** là một dự án Minecraft worldgen overhaul quy mô lớn, được phát triển trên Fabric.
- Độ cao thế giới: từ **-25,000** đến **+25,000** block.
- Biome rộng: trung bình **200–300 chunk** (~51200–76800 block).
- Hệ thống **MultiNoiseBiomeSource** tùy chỉnh, sử dụng **FastNoiseLite**.
- Hỗ trợ **150+ biome** với các cơ chế đặc biệt (BlizzardHell, EverestSummit, EchoingTundra...).

---

## 📂 Cấu trúc repo

```
HyperGenWorld/
 ├─ src/main/java/com/ducishere/hypergenworld/
 │   ├─ HyperBiomeSource.java
 │   ├─ HyperChunkGenerator.java
 │   ├─ FastNoiseLiteBackend.java
 │   └─ registry/...
 ├─ src/main/resources/
 │   ├─ data/hypergenworld/dimension_type/hyper_overworld.json
 │   ├─ data/hypergenworld/dimension/hyper_overworld.json
 │   └─ fabric.mod.json
 ├─ docs/
 │   ├─ worldgen-overhaul.md
 │   └─ dev-notes.md
 ├─ build.gradle
 └─ settings.gradle
```

---

## 🚀 Cách build

1. Clone repo:  
   ```bash
   git clone https://github.com/DucIsHere/HyperGenWorld.git
   cd HyperGenWorld
   ```

2. Build bằng Gradle:  
   ```bash
   ./gradlew build
   ```

3. File `.jar` nằm trong:  
   ```
   build/libs/
   ```

---

## 📝 Tài liệu

- [Worldgen Overhaul](docs/worldgen-overhaul.md) – giải thích hệ thống worldgen.  
- `dev-notes.md` – ghi chú dev, TODO list.  

---

## 🔧 Công nghệ

- **Minecraft:** 1.20.1 (Fabric API)  
- **Noise Lib:** FastNoiseLite  
- **Language:** Java 17  

---

## 📌 Credit

- Dev chính: **DucIsHere**  
- Ý tưởng & support: cộng đồng Minecraft modding VN  

---

## 📜 License

MIT License (tùy chọn, có thể đổi sau).
