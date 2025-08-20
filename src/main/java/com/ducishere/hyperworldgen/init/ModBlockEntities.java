package toughasnails.init;

import toughasnails.api.block.TANBlocks;
import toughasnails.api.blockentity.TANBlockEntityTypes;
import toughasnails.block.entity.TemperatureGaugeBlockEntity;
import toughasnails.block.entity.ThermoregulatorBlockEntity;
import toughasnails.block.entity.WaterPurifierBlockEntity;

public class ModBlockEntities {
    public static void register() {
        TANBlockEntityTypes.WATER_PURIFIER = RegistryHelper.registerBlockEntity(
                "water_purifier",
                WaterPurifierBlockEntity::new,
                TANBlocks.WATER_PURIFIER
        );

        TANBlockEntityTypes.TEMPERATURE_GAUGE = RegistryHelper.registerBlockEntity(
                "temperature_gauge",
                TemperatureGaugeBlockEntity::new,
                TANBlocks.TEMPERATURE_GAUGE
        );

        TANBlockEntityTypes.THERMOREGULATOR = RegistryHelper.registerBlockEntity(
                "thermoregulator",
                ThermoregulatorBlockEntity::new,
                TANBlocks.THERMOREGULATOR
        );
    }
}
