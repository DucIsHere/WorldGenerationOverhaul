package com.ducishere.hyperworldgen.world.pipeline;

import com.ducishere.hyperworldgen.world.noise.base.*;

public class AshenGraveIridge {
  public static double simple(double x, double y, double z, long seed) {
    double dune = new DuneBackend(seed, 0.03, 0.8).sample(x,y,z);
    double ridged = new RidgedBackend(seed, 0.05, 0.4).sample(x,y,z);
  }
}
