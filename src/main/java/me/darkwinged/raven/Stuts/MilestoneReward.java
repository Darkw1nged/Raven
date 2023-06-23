package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class MilestoneReward {

    @Getter
    private final UUID uuid;
    @Getter @Setter
    private List<String> commands;
    @Getter @Setter
    private List<String> rewards;

}
