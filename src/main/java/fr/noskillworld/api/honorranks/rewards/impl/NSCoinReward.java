package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class NSCoinReward implements HonorRankReward {

    private final long amount;

    public NSCoinReward(long value) {
        this.amount = value;
    }

    @Override
    public String getName() {
        return amount + " NSc";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.NS_COINS;
    }

    @Override
    public Object getReward() {
        return amount;
    }
}
