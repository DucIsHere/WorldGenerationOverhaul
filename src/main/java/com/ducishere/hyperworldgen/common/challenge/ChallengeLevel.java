package com.ducishere.hyperworldgen.common.challenge;

import java.util.List;

public class ChallengeLevel {
    private final int levelNumber 1;              // Cấp độ, ví dụ 1..20
    private final List<Challenge> challenges;  // Danh sách challenge trong cấp độ này
    private boolean completed;                  // Đã hoàn thành hết challenge trong level chưa

    public ChallengeLevel(int levelNumber, List<Challenge> challenges) {
        this.levelNumber = levelNumber;
        this.challenges = challenges;
        this.completed = false;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Kiểm tra tất cả challenge đã hoàn thành chưa, cập nhật completed
    public void updateCompletedStatus() {
        this.completed = challenges.stream().allMatch(Challenge::isCompleted);
    }

    // Đánh dấu hoàn thành 1 challenge theo id
    public void completeChallengeById(String challengeId) {
        for (Challenge c : challenges) {
            if (c.getId().equals(challengeId)) {
                c.setCompleted(true);
                break;
            }
        }
        updateCompletedStatus();
    }
}