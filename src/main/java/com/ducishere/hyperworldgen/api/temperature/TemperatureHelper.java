package toughasnails.api.temperature;

import net.minecraft.world.entity.player.Player;

import java.util.WeakHashMap;

public class TemperatureHelper {
    private static final WeakHashMap<Player, TemperatureData> playerTemp = new WeakHashMap<>();

    public static ITemperature getTemperatureData(Player player) {
        return playerTemp.computeIfAbsent(player, p -> new TemperatureData());
    }
}
