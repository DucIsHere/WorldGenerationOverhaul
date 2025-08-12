package com.ducishere.hyperworldgen.common.challenge;

public class Challenge {
    private final String id;           // ID duy nhất challenge, ví dụ: "chapter1_level2_ch5"
    private final String description;  // Mô tả challenge
    private boolean completed;         // Trạng thái đã hoàn thành
    private final int rewardPoints;    // Điểm thưởng khi hoàn thành

    private ChallengeReward reward;

    public Challenge(String id, String description, ChallengeReward reward) {
    this.id = id;
    this.description = description;
    this.reward = reward;
    this.completed = false;
    }

    public ChallengeReward getReward() {
    return reward;
    }

    public Challenge(String id, String description, int rewardPoints) {
        this.id = id;
        this.description = description;
        this.completed = false;
        this.rewardPoints = rewardPoints;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", rewardPoints=" + rewardPoints +
                '}';
    }
}
