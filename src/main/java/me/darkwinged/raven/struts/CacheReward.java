package me.darkwinged.raven.struts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class CacheReward {

    @Getter
    private final UUID uuid;
    @Getter
    private final String name;
    @Getter @Setter
    private String command;
    @Getter @Setter
    private int experience;
    @Getter @Setter
    private int coins;
    @Getter @Setter
    private String message;

}
