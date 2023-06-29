package me.darkwinged.raven.utilites;

import me.darkwinged.raven.struts.*;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

import java.util.*;

public class DataCache {

    public static Map<UUID, User> users = new HashMap<>();
    public static List<Guild> guilds = new ArrayList<>();
    public static List<Booster> activeBoosters = new ArrayList<>();
    public static List<Milestone> defaultUserMilestones = new ArrayList<>(Arrays.asList(
            new Milestone(UUID.randomUUID(), 1, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>())),
            new Milestone(UUID.randomUUID(), 5, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>())),
            new Milestone(UUID.randomUUID(), 10, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>())),
            new Milestone(UUID.randomUUID(), 25, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>())),
            new Milestone(UUID.randomUUID(), 50, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>())),
            new Milestone(UUID.randomUUID(), 100, false, false, new MilestoneReward(UUID.randomUUID(), new ArrayList<>(), new ArrayList<>()))
    ));

    public static Map<ArmorStand, Location> crates = new HashMap<>();

    // Cache gift milestone rewards lore
    public static List<String> cacheGiftMilestoneOne = new ArrayList<>(Arrays.asList(
        "&71x &6Celestial Benefactor Achievement",
        "&75x &dMystic Caches"
    ));
    public static List<String> cacheGiftMilestoneTwo = new ArrayList<>(List.of(
            "&710x &e⭐⭐⭐&7⭐⭐ &dMystic Caches"
    ));
    public static List<String> cacheGiftMilestoneThree = new ArrayList<>(Arrays.asList(
        "&75x &e⭐⭐⭐⭐&7⭐ &dMystic Caches",
        "&75x &e⭐⭐⭐⭐⭐ &dMystic Caches"
    ));
    public static List<String> cacheGiftMilestoneFour = new ArrayList<>(Arrays.asList(
            "&71x &6Legendary Giver's Crown",
            "&715x &e⭐⭐⭐⭐&7⭐ &dMystic Caches",
            "&710x &e⭐⭐⭐⭐⭐ &dMystic Caches"
    ));
    public static List<String> cacheGiftMilestoneFive = new ArrayList<>(Arrays.asList(
            "&71x &6Legendary Giver's Robes",
            "&725x &e⭐⭐⭐⭐⭐ &dMystic Caches"
    ));
    public static List<String> cacheGiftMilestoneSix = new ArrayList<>(Arrays.asList(
            "&71x &6Arcane Gifternio Familiar",
            "&775x &e⭐⭐⭐⭐⭐ &dMystic Caches"
    ));
    // Cache gift milestone rewards commands
    public static List<String> cacheGiftMilestoneOneCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 1 30 5"
    ));
    public static List<String> cacheGiftMilestoneTwoCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 3 30 10"
    ));
    public static List<String> cacheGiftMilestoneThreeCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 4 30 5",
            "cache give %player% cache 5 30 5"
    ));
    public static List<String> cacheGiftMilestoneFourCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 4 30 15",
            "cache give %player% cache 5 30 10"
    ));
    public static List<String> cacheGiftMilestoneFiveCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 5 30 25"
    ));
    public static List<String> cacheGiftMilestoneSixCommands = new ArrayList<>(Arrays.asList(
            "cache give %player% cache 5 30 75"
    ));

}
