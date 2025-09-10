package com.ducishere.hyperworldgen.registry;

import com.ducishere.hyperworldgen.world.pipeline.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HyperPipelineRegistry {
    private static final Map<String, Function<PipelineContext, Double>> PIPELINES = new HashMap<>();

    public static void register(String id, Function<PipelineContext, Double> fn) {
        PIPELINES.put(id, fn);
    }

    public static Function<PipelineContext, Double> get(String id) {
        return PIPELINES.get(id);
    }

    public static void init() {
        register("macro", ctx -> MacroPipeline.sample(ctx.x, ctx.y, ctx.z, ctx.seed));
        register("desert", ctx -> DesertPipeline.sample(ctx.x, ctx.y, ctx.z, ctx.seed));
        // TODO: mountain, ocean, snow...
    }

    public static class PipelineContext {
        public final double x, y, z;
        public final long seed;
        public PipelineContext(double x, double y, double z, long seed) {
            this.x = x; this.y = y; this.z = z; this.seed = seed;
        }
    }
}
