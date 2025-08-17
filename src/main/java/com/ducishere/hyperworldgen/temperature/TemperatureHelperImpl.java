package toughasnails.temperature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import toughasnails.api.temperature.*;

import java.util.*;

public class TemperatureHelperImpl implements TemperatureHelper.Impl.ITemperatureHelper {

    private static final List<IPositionalTemperatureModifier> positionalModifiers = new ArrayList<>(List.of(
        TemperatureHelperImpl::altitudeModifier,
        TemperatureHelperImpl::rainModifier
    ));

    private static final List<IProximityBlockModifier> proximityModifiers = new ArrayList<>();
    private static final List<IPlayerTemperatureModifier> playerModifiers = new ArrayList<>(List.of(
        TemperatureHelperImpl::thermoregulatorModifier,
        TemperatureHelperImpl::immersionModifier
    ));

    @Override
    public TemperatureLevel getTemperatureAtPos(Level level, BlockPos pos) {
        TemperatureLevel temp = getBiomeTemperatureLevel(level, pos);
        temp = nightModifier(level, pos, temp);

        for (IPositionalTemperatureModifier mod : positionalModifiers)
            temp = mod.modify(level, pos, temp);

        return proximityModifier(level, pos, temp);
    }

    @Override
    public ITemperature getPlayerTemperature(Player player) {
        return ((ITANPlayer) player).getTemperatureData();
    }

    @Override
    public boolean isTemperatureEnabled() {
        return ModConfig.temperature.enableTemperature;
    }

    // ----- Modifier methods -----
    private static TemperatureLevel altitudeModifier(Level level, BlockPos pos, TemperatureLevel current) {
        if (!level.dimensionType().natural()) return current;
        if (pos.getY() > ModConfig.temperature.temperatureDropAltitude) return current.decrement(1);
        if (pos.getY() < ModConfig.temperature.temperatureRiseAltitude) return current.increment(1);
        return current;
    }

    private static TemperatureLevel rainModifier(Level level, BlockPos pos, TemperatureLevel current) {
        if (isExposedToRain(level, pos))
            return current.increment(ModConfig.temperature.wetTemperatureChange);
        return current;
    }

    private static TemperatureLevel nightModifier(Level level, BlockPos pos, TemperatureLevel current) {
        float time = level.getTimeOfDay(1.0F);
        boolean isNight = time >= 0.25F && time <= 0.75F;
        if (isNight) return current.increment(ModConfig.temperature.nightTemperatureChange);
        return current;
    }

    private static TemperatureLevel proximityModifier(Level level, BlockPos pos, TemperatureLevel current) {
        // Check nearby heating/cooling blocks
        int heat = 0, cool = 0;
        // logic fill sets, AreaFill or custom nearby check
        return current.increment(heat - cool);
    }

    private static TemperatureLevel thermoregulatorModifier(Player player, TemperatureLevel current) { return current; }
    private static TemperatureLevel immersionModifier(Player player, TemperatureLevel current) { return current; }

    private static boolean isExposedToRain(Level level, BlockPos pos) { return level.isRaining(); }
    private static TemperatureLevel getBiomeTemperatureLevel(Level level, BlockPos pos) { return TemperatureLevel.NEUTRAL; }

    // ----- Register modifiers externally -----
    @Override public void registerPlayerTemperatureModifier(IPlayerTemperatureModifier mod) { playerModifiers.add(mod); }
    @Override public void registerPositionalTemperatureModifier(IPositionalTemperatureModifier mod) { positionalModifiers.add(mod); }
    @Override public void registerProximityBlockModifier(IProximityBlockModifier mod) { proximityModifiers.add(mod); }
}
