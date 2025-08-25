package com.ducishere.hyperworldgen.quest;

import dev.ftb.mods.ftblibrary.FTBLibrary;
import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import vazkii.patchouli.api.PatchouliAPI;
import net.minecraft.server.level.ServerPlayer;

public class QuestHandler {

    public static void register() {
        // Hook event player milestone / quest
        FTBLibrary.EVENT_BUS.addListener((ServerPlayer player) -> {
            // Kiểm tra milestone / quest hoàn thành
            if(playerCompletedQuest(player, "chapter_01_quest_01")) {
                // Unlock Patchouli chapter
                PatchouliAPI.instance.setBookFlag(player, "hyperworldgen:chapter_01", true);
                // Reward item / recipe
                FTBQuestsAPI.completeObjective(player, "chapter_01_quest_01");
            }
        });
    }

    private static boolean playerCompletedQuest(ServerPlayer player, String questId) {
        // TODO: Thay bằng logic FTB Quests API
        return false;
    }
}
