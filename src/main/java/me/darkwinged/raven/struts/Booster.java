package me.darkwinged.raven.struts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class Booster {

    @Getter
    private final UUID uuid;
    @Getter @Setter
    private int tier;
    @Getter @Setter
    private double experienceMultiplier;
    @Getter @Setter
    private double coinMultiplier;
    @Getter @Setter
    private long duration;

}
