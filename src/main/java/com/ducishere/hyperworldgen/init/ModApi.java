package toughasnails.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import toughasnails.api.temperature.TemperatureHelper;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.temperature.TemperatureHelperImpl;
import toughasnails.thirst.ThirstHelperImpl;

public class ModApi
{
    private static final Logger LOGGER = LogManager.getLogger("ToughAsNails|API");

    public static void init()
    {
        // Khởi tạo Thirst API
        if (ThirstHelper.Impl.INSTANCE == null) {
            ThirstHelper.Impl.INSTANCE = new ThirstHelperImpl();
            LOGGER.info("ThirstHelper API initialized.");
        } else {
            LOGGER.warn("ThirstHelper API was already initialized!");
        }

        // Khởi tạo Temperature API
        if (TemperatureHelper.Impl.INSTANCE == null) {
            TemperatureHelper.Impl.INSTANCE = new TemperatureHelperImpl();
            LOGGER.info("TemperatureHelper API initialized.");
        } else {
            LOGGER.warn("TemperatureHelper API was already initialized!");
        }
    }
}
