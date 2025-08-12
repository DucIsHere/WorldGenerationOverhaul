package com.ducishere.hyperworldgen.common.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChallengeManager {
    private final List<Chapter> chapters;
    private Chapter currentChapter;
    private ChallengeLevel currentLevel;

    public ChallengeManager(List<Chapter> chapters) {
        this.chapters = chapters;
        if (!chapters.isEmpty()) {
            this.currentChapter = chapters.get(0);
            this.currentChapter.setUnlocked(true);
            if (!currentChapter.getLevels().isEmpty()) {
                this.currentLevel = currentChapter.getLevels().get(0);
            }
        }
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public Chapter getCurrentChapter() {
        return currentChapter;
    }

    public ChallengeLevel getCurrentLevel() {
        return currentLevel;
    }

    // Đánh dấu challenge theo id hoàn thành, update trạng thái level, chapter
    public void completeChallenge(String challengeId) {
        boolean updated = false;

        for (Chapter chapter : chapters) {
            for (ChallengeLevel level : chapter.getLevels()) {
                Optional<Challenge> challengeOpt = level.getChallenges().stream()
                        .filter(c -> c.getId().equals(challengeId))
                        .findFirst();
                if (challengeOpt.isPresent()) {
                    Challenge challenge = challengeOpt.get();
                    if (!challenge.isCompleted()) {
                        challenge.setCompleted(true);
                        updated = true;
                    
                            if (killer instanceof ServerPlayerEntity player) {
                                if (challenge.getReward() != null) {
                                    challenge.getReward().giveToPlayer(player);
                                }
                             }
 
                        level.updateCompletedStatus();
                        chapter.updateCompletedStatus();

                        // Mở khóa level tiếp theo nếu cần
                        if (chapter.canUnlockNextLevel(level.getLevelNumber())) {
                            int nextLevelIndex = level.getLevelNumber();
                            if (nextLevelIndex < chapter.getLevels().size()) {
                                chapter.getLevels().get(nextLevelIndex).updateCompletedStatus(); // reset hoặc kiểm tra lại
                            }
                        }

                        // Mở khóa chương tiếp theo nếu cần
                        if (chapter.canUnlockNextChapter()) {
                            int nextChapterIndex = chapters.indexOf(chapter) + 1;
                            if (nextChapterIndex < chapters.size()) {
                                chapters.get(nextChapterIndex).setUnlocked(true);
                            }
                        }
                    }
                }
            }
        }

        if (updated) {
            // TODO: Lưu trạng thái, trigger event thông báo hoàn thành challenge
            System.out.println("Challenge " + challengeId + " đã hoàn thành!");
        }
    }

    // Check trạng thái tất cả challenge đã hoàn thành
    public boolean isAllCompleted() {
        return chapters.stream().allMatch(Chapter::isCompleted);
    }

    // TODO: Hàm load/save trạng thái challenge (từ file, capability...)
}