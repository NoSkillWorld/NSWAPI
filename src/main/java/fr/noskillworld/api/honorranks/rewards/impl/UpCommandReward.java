package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class UpCommandReward implements HonorRankReward {

    @Override
    public String getName() {
        return "/up";
    }

    @Override
    public String getDescription() {
        return "Accès à la commande /up";
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.COMMAND;
    }

    @Override
    public Object getReward() {
        return "nsw.command.up";
    }
}
