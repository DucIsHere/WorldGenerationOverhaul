package com.ducishere.hyperworldgen.util;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static float nextFloat() {
        return RANDOM.nextFloat();
    }

    public static boolean chance(float probability) {
        return RANDOM.nextFloat() < probability;
    }

    public static int rangeInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static float rangeFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }
}