package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class ITFToggleModeCommandReward implements HonorRankReward {

    @Override
    public String getName() {
        return "/itf togglemode visibility|glow";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.COMMAND;
    }

    @Override
    public Object getReward() {
        return "invisibleitemframes.command.togglemode.*";
    }
}
