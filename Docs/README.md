📘 Usage Guide – HyperGenWorld

This document explains how to use **HyperGenWorld** in development and in Minecraft.

---

## 🔧 Installation (Player)

1. Download the `.jar` file from [Releases](../build/libs/).
2. Place the file into your Minecraft `mods/` folder.  
   - Fabric: `~/.minecraft/mods/`  
   - MultiMC/Prism: instance `mods/` folder  
3. Launch Minecraft with **Fabric Loader 1.20.1+**.

---

## 💻 Development Setup

Clone the repository:

```bash
git clone https://github.com/DucIsHere/HyperGenWorld.git
cd HyperGenWorld
Run dev client:
./gradlew runClient
Build mod:
./gradlew build
```

---

🛠️ Example: Adding a Custom Block
Preview
Here’s how to define and register a new block inside HyperGenWorld.
Blocks are registered in registry/ModBlocks.java.
Code :

```java
public class ModBlocks {
    public static final Block BLUE_STONE = new Block(
        FabricBlockSettings.of(Material.STONE).strength(6.0f, 10.0f).requiresTool()
    );

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, new Identifier("hypergenworld", "blue_stone"), BLUE_STONE);
        Registry.register(Registries.ITEM, new Identifier("hypergenworld", "blue_stone"),
            new BlockItem(BLUE_STONE, new Item.Settings()));
    }
}
```

Now the block blue_stone will appear in-game with higher hardness.

---

🌍 Example: Custom Worldgen
Preview
Modify HyperChunkGenerator to include a new noise layer for ores.
Code :
// Inside HyperChunkGenerator.java

```java
private void generateBlueStoneOre(ChunkRegion region, Random random, BlockPos origin) {
    int veinSize = 8;
    for (int i = 0; i < veinSize; i++) {
        int x = origin.getX() + random.nextInt(16);
        int y = random.nextInt(200); // up to Y=200
        int z = origin.getZ() + random.nextInt(16);
        BlockPos pos = new BlockPos(x, y, z);
        if (region.getBlockState(pos).isOf(Blocks.STONE)) {
            region.setBlockState(pos, ModBlocks.BLUE_STONE.getDefaultState(), Block.NOTIFY_ALL);
        }
    }
}
```
✅ Summary
Place .jar in mods/ to play.
Use Gradle tasks for dev (runClient, build).
Add features by editing registry classes or chunk generator.

---

## 📄 `docs/dev-notes.md`

```markdown
# 🛠️ Developer Notes – HyperGenWorld

This file contains internal notes and TODOs for ongoing development.
```

---

## 🔍 Current Features
- Custom world height: `-25,000` to `+25,000`
- 150+ biomes using **MultiNoiseBiomeSource**
- Noise backend: **FastNoiseLite**
- Large biome size: `200–300 chunks`

---

## 📝 Development Tips

### Registry
- Keep all **blocks** in `ModBlocks.java`
- Keep all **items** in `ModItems.java`
- Keep all **worldgen** configs in `ModWorldGen.java`

### Debugging
- Use `System.out.println()` for quick logs
- Use `./gradlew runClient --debug-jvm` to attach debugger

---

## 📌 TODO
- [x] Add **VolcanoBiome**
- [ ] Optimize chunk generator performance
- [ ] Port to **Quilt Loader**
- [ ] Configurable biome size via JSON

---

## ⚠️ Known Issues
- Worldgen at extreme Y (`>+20000` or `<-20000`) may cause lag spikes
- Some custom biomes (e.g., **EchoingTundra**) missing ambient sounds
