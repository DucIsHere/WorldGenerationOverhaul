package com.ducishere.hyperworldgen.common.challenge;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class ChallengeDataSaver {

    private final ChallengeManager manager;

    public ChallengeDataSaver(ChallengeManager manager) {
        this.manager = manager;
    }

    // Lưu trạng thái
    public void save(ServerPlayerEntity player) {
        NbtCompound nbt = new NbtCompound();

        // Lưu từng challenge
        manager.getChapters().forEach(chapter -> {
            NbtCompound chapterTag = new NbtCompound();
            chapterTag.putBoolean("unlocked", chapter.isUnlocked());
            chapterTag.putBoolean("completed", chapter.isCompleted());

            chapter.getLevels().forEach(level -> {
                NbtCompound levelTag = new NbtCompound();
                levelTag.putBoolean("completed", level.isCompleted());

                level.getChallenges().forEach(challenge -> {
                    levelTag.putBoolean(challenge.getId(), challenge.isCompleted());
                });

                chapterTag.put("level_" + level.getLevelNumber(), levelTag);
            });

            nbt.put("chapter_" + chapter.getChapterNumber(), chapterTag);
        });

        player.getPersistentData().put("hyperworld_challenges", nbt);
    }

    // Load trạng thái
    public void load(ServerPlayerEntity player) {
        if (!player.getPersistentData().contains("hyperworld_challenges")) return;

        NbtCompound nbt = player.getPersistentData().getCompound("hyperworld_challenges");

        manager.getChapters().forEach(chapter -> {
            String chapterKey = "chapter_" + chapter.getChapterNumber();
            if (nbt.contains(chapterKey)) {
                NbtCompound chapterTag = nbt.getCompound(chapterKey);
                chapter.setUnlocked(chapterTag.getBoolean("unlocked"));
                chapter.updateCompletedStatus();

                chapter.getLevels().forEach(level -> {
                    String levelKey = "level_" + level.getLevelNumber();
                    if (chapterTag.contains(levelKey)) {
                        NbtCompound levelTag = chapterTag.getCompound(levelKey);
                        level.updateCompletedStatus();

                        level.getChallenges().forEach(challenge -> {
                            if (levelTag.contains(challenge.getId())) {
                                challenge.setCompleted(levelTag.getBoolean(challenge.getId()));
                            }
                        });
                    }
                });
            }
        });
    }
}