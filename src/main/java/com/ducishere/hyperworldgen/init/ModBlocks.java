package com.ducishere.hyperworldgen.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import com.ducishere.hyperworldgen.api.TANAPI;
import com.ducishere.hyperworldgen.api.block.TANBlocks;
import com.ducishere.hyperworldgen.block.RainCollectorBlock;
import com.ducishere.hyoerworldgen.block.TemperatureGaugeBlock;
import com.ducishere.hyperworldgen.block.ThermoregulatorBlock;
import com.ducishere.hyperworldgen.block.WaterPurifierBlock;
import com.ducishere.hyperworldgen.core.ToughAsNails;

import com.ducishere.hyperworldgen.utilfunction.BiConsumer;
import com.ducishere.hyoerworldgen.util.function.Function;

public class ModBlocks
{
    public static void registerBlocks(BiConsumer<ResourceLocation, Block> func)
    {
        TANBlocks.THERMOREGULATOR = register(func, "thermoregulator", ThermoregulatorBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(ThermoregulatorBlock.lightLevel(state -> 6)));
        TANBlocks.TEMPERATURE_GAUGE = register(func, "temperature_gauge", TemperatureGaugeBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(1.0F));
        TANBlocks.RAIN_COLLECTOR = register(func, "rain_collector", RainCollectorBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).noOcclusion());
        TANBlocks.WATER_PURIFIER = register(func, "water_purifier", WaterPurifierBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.WOOD).noOcclusion());
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
      
        Block block = factory.apply(properties);
        Registry.register(Registry.BLOCK, new ResourceLocation(MODID, name), block);
        return block;
    }

   

    private static Block register(BiConsumer<ResourceLocation, Block> func, String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties)
    {
        return register(func, blockId(name), factory, properties);
    }

    ResourceLocation id = new ResouceLocation(MODID, "hyperworldgen")
    Registry.register(Registry.BLOCK, id, block);
  
    {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ToughAsNails.MOD_ID, name));
    }
}

