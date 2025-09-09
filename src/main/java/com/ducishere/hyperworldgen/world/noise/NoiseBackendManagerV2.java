package com.ducishere.hyperworldgen.world.noise;

public class NoiseBackendManagerV2 {

    public static double sample(String name, double x, double y, double z) {
        switch (name.toLowerCase()) {
            // --- Base noises ---
            case "opensimplex": return OpenSimplexBackend.sample(x,y,z);
            case "perlinclassic": return PerlinClassicBackend.sample(x,y,z);
            case "simplex": return SimplexBackend.sample(x,y,z);
            case "fastnoise": return FastNoiseBackend.sample(x,y,z);
            case "fbm": return FBMBackend.sample(x,y,z);
            case "octavesum": return OctaveSumBackend.sample(x,y,z);

            // --- Ridged / Mountains ---
            case "ridged": return RidgedBackend.sample(x,y,z);
            case "ridgedperlin": return RidgedPerlinBackend.sample(x,y,z);
            case "fractalridged": return FractalRidgedBackend.sample(x,y,z);

            // --- Domain warp / Hybrid ---
            case "domainwarp": return DomainWarpBackend.sample(x,y,z);
            case "hybrid": return HybridBackend.sampleAll(x,y,z);
            case "terrablend": return TerraBlendBackend.sample(x,y,z);

            // --- Terrace / Fields ---
            case "terrace": return TerraceBackend.sample(x,y,z);
            case "terracestep": return TerraceStepBackend.sample(x,y,z);
            case "cropfield": return CropFieldBackend.sample(x,y,z);
            case "terracefield": return TerraceFieldBackend.sample(x,y,z);

            // --- Cellular / Voronoi ---
            case "cellular": return CellularBackend.sample(x,y,z);
            case "cellulardistance": return CellularDistanceBackend.sample(x,y,z);
            case "voronoi": return VoronoiBackend.sample(x,y,z);
            case "worley": return WorleyBackend.sample(x,y,z);
            case "sparsevoronoi": return SparseVoronoiBackend.sample(x,y,z);
            case "archipelago": return ArchipelagoBackend.sample(x,y,z);

            // --- Cave / Underground ---
            case "cave": return CaveBackend.sample(x,y,z);
            case "worm": return WormNoiseBackend.sample(x,y,z);
            case "voronoicave": return VoronoiCaveBackend.sample(x,y,z);
            case "lava": return LavaPocketBackend.sample(x,y,z);
            case "sponge": return SpongeBackend.sample(x,y,z);
            case "bubble": return BubbleBackend.sample(x,y,z);

            // --- Rivers / Water ---
            case "river": return RiverBackend.sample(x,y,z);
            case "riverbank": return RiverBankBackend.sample(x,y,z);
            case "delta": return DeltaBackend.sample(x,y,z);
            case "waterfall": return WaterfallBackend.sample(x,y,z);
            case "oceanfloor": return OceanFloorBackend.sample(x,y,z);
            case "coralreef": return CoralReefBackend.sample(x,y,z);

            // --- Volcano / Thermal ---
            case "volcano": return VolcanoBackend.sample(x,y,z);
            case "thermal": return ThermalBackend.sample(x,y,z);

            // --- Ice / Snow ---
            case "glacier": return GlacierBackend.sample(x,y,z);
            case "snowpatch": return SnowPatchBackend.sample(x,y,z);

            // --- Desert / Mesa ---
            case "dune": return DuneBackend.sample(x,y,z);
            case "sandpatch": return SandPatchBackend.sample(x,y,z);
            case "mesapatch": return MesaPatchBackend.sample(x,y,z);

            // --- Erosion / Geology ---
            case "erosion": return ErosionBackend.sample(x,y,z);
            case "sediment": return SedimentBackend.sample(x,y,z);
            case "fault": return CrackFaultBackend.sample(x,y,z);
            case "hydraulic": return HydraulicErosionBackend.sample(x,y,z);
            case "thermalerosion": return ThermalErosionBackend.sample(x,y,z);

            // --- Climate / Biome ---
            case "climate": return ClimateNoiseBackend.sample(x,y,z);
            case "biomeblend": return BiomeBlendBackend.sample(x,y,z);
            case "continentalness": return ContinentalnessBackend.sample(x,y,z);

            // --- Vegetation / Patches ---
            case "moss": return MossBackend.sample(x,y,z);
            case "tree": return TreePlacementBackend.sample(x,y,z);
            case "patch": return PatchBackend.sample(x,y,z);
            case "lichen": return LichenBackend.sample(x,y,z);

            // --- Exotic patterns ---
            case "reactiondiffusion": return ReactionDiffusionBackend.sample(x,y,z);
            case "gabor": return GaborBackend.sample(x,y,z);
            case "curl": return CurlBackend.sample(x,y,z);
            case "flow": return FlowBackend.sample(x,y,z);
            case "phaseshift": return PhaseShiftedBackend.sample(x,y,z);
            case "wavelet": return WaveletBackend.sample(x,y,z);

            // --- Spikes / Monolith ---
            case "spike": return SpikeBackend.sample(x,y,z);
            case "sparsepoint": return SparsePointBackend.sample(x,y,z);
            case "densepoint": return DensePointBackend.sample(x,y,z);

            // --- End / Void / Lightning ---
            case "void": return VoidBackend.sample(x,y,z);
            case "lightning": return LightningPathBackend.sample(x,y,z);

            // --- Utils / Combiner ---
            case "quantize": return QuantizeBackend.sample(x,y,z);
            case "mask": return NoiseMaskBackend.sample(x,y,z);
            case "index": return IndexNoiseBackend.sample(x,y,z);
            case "multiscale": return MultiScaleBackend.sample(x,y,z);
            case "noisecmb": return NoiseCombinerBackend.sample(x,y,z);

            default: return 0.0;
        }
    }
}
