package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class Milestone {

    @Getter
    private final UUID uuid;
    @Getter @Setter
    private int required;
    @Getter @Setter
    private boolean reached;
    @Getter @Setter
    private boolean claimed;
    @Getter @Setter
    private MilestoneReward reward;


}
