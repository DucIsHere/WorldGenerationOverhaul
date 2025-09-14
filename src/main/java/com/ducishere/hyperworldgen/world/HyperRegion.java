package com.ducishere.hyperworldgen.world;

import com.mojang.datafixers.util.Pair;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.ParameterUtils;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.Climate;

public class HyperRegion extends Region {

    public static final Identifier LOCATION = new Identifier("hyperworldgen", "hyper_region");

    public HyperRegion(int weight) {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(RegistryWrapper.WrapperLookup registryLookup,
                          Consumer<Pair<Climate.ParameterPoint, RegistryKey<Biome>>> mapper) {

        // ----- Rice Tropical (cực rộng 50~75 km) -----
        Climate.Parameter temp = Climate.Parameter.span(0.8F, 1.0F); // nóng
        Climate.Parameter humidity = Climate.Parameter.span(0.8F, 1.0F); // ẩm
        Climate.Parameter erosion = Climate.Parameter.span(-0.2F, 0.6F); // hơi phẳng
        Climate.Parameter depth = Climate.Parameter.point(0.0F);
        Climate.Parameter weirdness = Climate.Parameter.span(-0.6F, 0.6F);

        mapper.accept(Pair.of(Climate.parameters(
            temp, humidity, continentalness, erosion, depth, weirdness, 0.0F
        ), RegistryKey.of(RegistryKeys.BIOME, new Identifier("modid", "rice_tropical"))));

        // ----- Nepal Mountain (~12 km) -----
        mapper.accept(Pair.of(Climate.parameters(
            Climate.Parameter.span(-0.8F, -0.6F), // lạnh
            Climate.Parameter.span(0.3F, 0.6F),   // trung bình ẩm
            Climate.Parameter.span(-0.5F, 0.5F),  // trung bình
            Climate.Parameter.span(-1.0F, -0.7F), // erosion thấp -> núi dựng đứng
            Climate.Parameter.point(0.8F),        // cao
            Climate.Parameter.span(0.2F, 0.4F),
            0.0F
        ), RegistryKey.of(RegistryKeys.BIOME, new Identifier("modid", "nepal_mountain"))));

        // ----- Everest Summit (~17 km, cực hẹp) -----
        mapper.accept(Pair.of(Climate.parameters(
            Climate.Parameter.span(-1.0F, -0.9F), // siêu lạnh
            Climate.Parameter.span(0.2F, 0.5F),
            Climate.Parameter.span(-0.2F, 0.2F),
            Climate.Parameter.span(-1.0F, -0.9F), // erosion rất thấp
            Climate.Parameter.point(1.0F),        // cao nhất
            Climate.Parameter.point(0.9F),        // weirdness -> extreme
            0.0F
        ), RegistryKey.of(RegistryKeys.BIOME, new Identifier("modid", "everest_summit"))));

        // ----- Blizzard Hell (bao quanh cao nguyên) -----
        mapper.accept(Pair.of(Climate.parameters(
            Climate.Parameter.span(-1.0F, -0.7F), // lạnh
            Climate.Parameter.span(0.8F, 1.0F),   // cực ẩm
            Climate.Parameter.span(-0.3F, 0.3F),
            Climate.Parameter.span(-0.5F, 0.2F),
            Climate.Parameter.point(0.5F),
            Climate.Parameter.span(-0.4F, 0.4F),
            0.0F
        ), RegistryKey.of(RegistryKeys.BIOME, new Identifier("modid", "blizzard_hell"))));

     
    }

    // ----- Đăng ký region -----
    public static void register() {
        Regions.register(new HyperRegion(5)); // weight 5 = ưu tiên vừa
    }
}
