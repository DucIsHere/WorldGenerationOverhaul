package toughasnails.temperature;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureLevel;
import toughasnails.init.ModConfig;

import java.util.HashSet;
import java.util.Set;

public class TemperatureData implements ITemperature {

    public static final TemperatureLevel DEFAULT_LEVEL = TemperatureLevel.NEUTRAL;

    private TemperatureLevel level = DEFAULT_LEVEL;
    private TemperatureLevel targetLevel = DEFAULT_LEVEL;
    private int changeDelayTicks;
    private int hyperthermiaTicks;
    private int dryTicks;
    private int extremityDelayTicks;

    private Set<BlockPos> nearbyThermoregulators = new HashSet<>();
    private TemperatureLevel lastLevel = DEFAULT_LEVEL;
    private int lastHyperthermiaTicks;
    private Set<BlockPos> lastNearbyThermoregulators = new HashSet<>();

    @Override
    public TemperatureLevel getLevel() { return level; }

    @Override
    public TemperatureLevel getTargetLevel() { return targetLevel; }

    @Override
    public int getChangeDelayTicks() { return changeDelayTicks; }

    @Override
    public int getHyperthermiaTicks() { return hyperthermiaTicks; }

    @Override
    public int getExtremityDelayTicks() { return extremityDelayTicks; }

    @Override
    public int getDryTicks() { return dryTicks; }

    @Override
    public TemperatureLevel getLastLevel() { return lastLevel; }

    @Override
    public int getLastHyperthermiaTicks() { return lastHyperthermiaTicks; }

    @Override
    public Set<BlockPos> getNearbyThermoregulators() { return nearbyThermoregulators; }

    @Override
    public Set<BlockPos> getLastNearbyThermoregulators() { return lastNearbyThermoregulators; }

    @Override
    public void setLevel(TemperatureLevel level) { this.level = level; }

    @Override
    public void setTargetLevel(TemperatureLevel level) { this.targetLevel = level; }

    @Override
    public void setChangeDelayTicks(int ticks) { this.changeDelayTicks = ticks; }

    @Override
    public void setHyperthermiaTicks(int ticks) { this.hyperthermiaTicks = ticks; }

    @Override
    public void setExtremityDelayTicks(int ticks) { this.extremityDelayTicks = ticks; }

    @Override
    public void setDryTicks(int ticks) { this.dryTicks = ticks; }

    @Override
    public void setLastLevel(TemperatureLevel level) { this.lastLevel = level; }

    @Override
    public void setLastHyperthermiaTicks(int ticks) { this.lastHyperthermiaTicks = ticks; }

    @Override
    public void setNearbyThermoregulators(Set<BlockPos> values) { this.nearbyThermoregulators = values; }

    @Override
    public void setLastNearbyThermoregulators(Set<BlockPos> values) { this.lastNearbyThermoregulators = values; }

    // Save data
    public void addAdditionalSaveData(ValueOutput output) {
        output.putInt("temperatureLevel", level.ordinal());
        output.putInt("targetTemperatureLevel", targetLevel.ordinal());
        output.putInt("changeDelayTicks", changeDelayTicks);
        output.putInt("hyperthermiaTicks", hyperthermiaTicks);
        output.putInt("extremityDelayTicks", extremityDelayTicks);
        output.putInt("dryTicks", dryTicks);
    }

    // Load data
    public void readAdditionalSaveData(ValueInput input) {
        this.level = TemperatureLevel.values()[input.getInt("temperatureLevel").orElse(DEFAULT_LEVEL.ordinal())];
        this.targetLevel = TemperatureLevel.values()[input.getInt("targetTemperatureLevel").orElse(DEFAULT_LEVEL.ordinal())];
        this.changeDelayTicks = input.getInt("changeDelayTicks").orElse(0);
        this.hyperthermiaTicks = input.getInt("hyperthermiaTicks").orElse(0);
        this.extremityDelayTicks = input.getInt("extremityDelayTicks").orElse(0);
        this.dryTicks = input.getInt("dryTicks").orElse(0);
    }
}
