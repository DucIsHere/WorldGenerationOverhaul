package com.ducishere.hyperworldgen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("HyperWorldGen");

    public static void info(String message) {
        LOGGER.info("[HyperWorldGen] " + message);
    }

    public static void warn(String message) {
        LOGGER.warn("[HyperWorldGen] " + message);
    }

    public static void error(String message) {
        LOGGER.error("[HyperWorldGen] " + message);
    }

    public static void debug(String message) {
        LOGGER.debug("[HyperWorldGen] " + message);
    }
}