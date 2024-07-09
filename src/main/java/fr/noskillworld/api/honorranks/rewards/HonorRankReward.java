package fr.noskillworld.api.honorranks.rewards;

public interface HonorRankReward {

    String getName();

    String getDescription();

    RewardType getRewardType();

    Object getReward();

}
