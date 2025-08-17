package com.ducishere.hyperworldgen.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent FREE_LUCK_DISC;

    public static void registerSounds() {
        FREE_LUCK_DISC = Registry.register(Registries.SOUND_EVENT, new Identifier("hyperworldgen", "music_disc_free_luck"),
                new SoundEvent(new Identifier("hyperworldgen", "music_disc_free_luck")));
        System.out.println("Sounds registered");
    }
}
