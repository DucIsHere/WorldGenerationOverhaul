package com.ducishere.hyperworldgen:

import com.ducishere.hyperworldgen.screen.Screen;
import com.ducishere.hyperworldgen.screen.ScreenHandler;
import com.ducishere.hyperworldgen.block.entity.ModBlockEntity;
import net.fabricmc.fabric.api.event.lifecycle.v1.SeverTickEvents;
import net.minecraft.sever.world.ServerWorld;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen;
import net.fabricmc.fabric.api.client.screen.v1;
import net.fabricmc.fabric.api.client.screenhandler.v1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HyperWorldGenClient implement ClientModInitializer {

  public static final Strings MOD_ID = "hyperworldgen";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public static void init() {
    LOGGER.info("HyperWorldGen initialized!")
  }
    

  @Override
  public void onInitializerClient() {
    ScreenRegistry.Factory();
  }
  
}
