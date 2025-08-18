package com.ducishere.hyperworldgen.screen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<CryoFurnaceScreenHandler> CRYO_FURNACE_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        CRYO_FURNACE_SCREEN_HANDLER = Registry.register(
                Registries.SCREEN_HANDLER,
                new Identifier("hyperworldgen", "cryo_furnace"),
                new ScreenHandlerType<>(CryoFurnaceScreenHandler::new)
        );
    }
}
