package com.ducishere.hyperworldgen.common.challenge;

import java.util.List;

public class Chapter {
    private final int chapterNumber;            // Số chương, ví dụ 1..150
    private final List<ChallengeLevel> levels; // Danh sách các level trong chương
    private boolean unlocked;                   // Đã mở khóa chương chưa
    private boolean completed;                  // Đã hoàn thành tất cả level trong chương chưa

    public Chapter(int chapterNumber, List<ChallengeLevel> levels) {
        this.chapterNumber = chapterNumber;
        this.levels = levels;
        this.unlocked = false;
        this.completed = false;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public List<ChallengeLevel> getLevels() {
        return levels;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Kiểm tra hoàn thành tất cả level trong chương, cập nhật completed
    public void updateCompletedStatus() {
        this.completed = levels.stream().allMatch(ChallengeLevel::isCompleted);
    }

    // Mở khóa level kế tiếp nếu level hiện tại hoàn thành
    public boolean canUnlockNextLevel(int currentLevelNumber) {
        if (currentLevelNumber < levels.size()) {
            ChallengeLevel currentLevel = levels.get(currentLevelNumber - 1);
            return currentLevel.isCompleted();
        }
        return false;
    }

    // Mở khóa chương kế tiếp nếu chương này hoàn thành
    public boolean canUnlockNextChapter() {
        return completed;
    }
}