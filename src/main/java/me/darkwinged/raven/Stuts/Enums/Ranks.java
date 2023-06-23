package me.darkwinged.raven.Stuts.Enums;

import lombok.Getter;

public enum Ranks {

    DEFAULT("Default", "§7", 0, ""),
    VIP("VIP", "§a", 1, "VIP"),
    VIP_PLUS("VIP+", "§a", 2, "VIP+"),
    MVP("MVP", "§b", 3, "MVP"),
    MVP_PLUS("MVP+", "§b", 4, "MVP+"),
    MVP_PLUS_PLUS("MVP++", "§b", 5, "MVP++"),
    YOUTUBER("Youtuber", "§c", 6, "Youtuber"),
    HELPER("Helper", "§9", 7, "Helper"),
    MODERATOR("Moderator", "§2", 8, "Mod"),
    ADMIN("Admin", "§c", 9, "Admin"),
    OWNER("Owner", "§4", 10, "Owner");

    @Getter
    private final String name;
    @Getter
    private final String color;
    @Getter
    private final int priority;
    @Getter
    private final String prefix;

    Ranks(String name, String color, int priority, String prefix) {
        this.name = name;
        this.color = color;
        this.priority = priority;
        this.prefix = prefix;
    }

}
