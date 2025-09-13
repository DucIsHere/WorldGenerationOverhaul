package com.ducishere.hyperworldgen.sound;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.sound.MusicSound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class HyperMusic {
    public static final Identifier FREE_LUCK_ID = new Identifier("modid", "music.music_disc_free_luck");
    public static final SoundEvent FREE_LUCK_EVENT = SoundEvent.of(FREE_LUCK_ID);

    public static final MusicSound CUSTOM_MENU = new MusicSound(
        RegistryKey.of(RegistryKeys.SOUND_EVENT, FREE_LUCK_ID),
        0, 0, true
    );

    public static void init() {
        // Đăng ký sound event
        Registry.register(Registries.SOUND_EVENT, FREE_LUCK_ID, FREE_LUCK_EVENT);

        // Gán title screen music = custom disc
        TitleScreen.MUSIC = CUSTOM_MENU;
    }
}
