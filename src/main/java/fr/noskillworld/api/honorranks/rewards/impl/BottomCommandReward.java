package fr.noskillworld.api.honorranks.rewards.impl;

import fr.noskillworld.api.honorranks.rewards.HonorRankReward;
import fr.noskillworld.api.honorranks.rewards.RewardType;

public class BottomCommandReward implements HonorRankReward {

    @Override
    public String getName() {
        return "/bottom";
    }

    @Override
    public String getDescription() {
        return "Accès à la commande §3" + getName();
    }

    @Override
    public RewardType getRewardType() {
        return RewardType.COMMAND;
    }

    @Override
    public Object getReward() {
        return "nsw.command.bottom";
    }
}
