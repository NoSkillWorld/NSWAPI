package fr.noskillworld.api.honorranks.rewards;

public interface HonorRankReward {

    default String getId() {
        return getName().toLowerCase().replace(" ", "_").replace("/", "");
    }

    String getName();

    String getDescription();

    RewardType getRewardType();

    Object getReward();

}
