package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class DownCommandReward implements HonorRankReward {

    @Override
    public String getName() {
        return "/down";
    }

    @Override
    public String getDescription() {
        return "Accès à la commande /down";
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.COMMAND;
    }

    @Override
    public Object getReward() {
        return "nsw.command.down";
    }
}
