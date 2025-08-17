package toughasnails.temperature;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import toughasnails.api.temperature.ITemperature;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.temperature.TemperatureLevel;
import toughasnails.init.ModConfig;
import toughasnails.network.UpdateTemperaturePacket;

public class TemperatureHandler {

    public static void onPlayerTick(Player player) {
        if (!ModConfig.temperature.enableTemperature || player.level().isClientSide()) return;

        ITemperature data = TemperatureHelper.getTemperatureData(player);
        TemperatureLevel target = TemperatureHelper.getTemperatureAtPos(player.level(), player.blockPosition());

        if (data.getChangeDelayTicks() == 0)
            data.setLevel(data.getLevel().increment(target.ordinal() - data.getLevel().ordinal()));
        else
            data.setChangeDelayTicks(data.getChangeDelayTicks() - 1);

        // Hyperthermia
        if (data.getLevel() == TemperatureLevel.HOT) {
            data.setHyperthermiaTicks(Math.min(TemperatureHelper.getTicksRequiredForHyperthermia(),
                    data.getHyperthermiaTicks() + 1));
        } else data.setHyperthermiaTicks(Math.max(0, data.getHyperthermiaTicks() - 1));
    }

    public static void syncTemperature(ServerPlayer player) {
        ITemperature temp = TemperatureHelper.getTemperatureData(player);
        UpdateTemperaturePacket packet = new UpdateTemperaturePacket(
                temp.getLevel(),
                temp.getHyperthermiaTicks(),
                temp.getNearbyThermoregulators()
        );
        // send packet to client
    }
}
