package com.ducishere.hyperworldgen.pipeline;

public interface Pipeline {
    double sample(double x, double y, double z, long seed);
}
