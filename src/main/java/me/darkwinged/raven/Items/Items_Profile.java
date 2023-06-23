package me.darkwinged.raven.Items;

import me.darkwinged.raven.Stuts.Achievement;
import me.darkwinged.raven.Stuts.Quest;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Items_Profile {

    public static ItemStack profile(User user) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        Player player = Bukkit.getPlayer(user.getUuid());
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setOwningPlayer(player);
        meta.setDisplayName(Utils.color("&aProfile"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Level: &d" + user.getLevel(),
                "&7Achievements Completed: &d" + user.getAchievements().stream().filter(Achievement::isCompleted).count(),
                "&7Quests Completed: &d" + user.getQuests().stream().filter(Quest::isCompleted).count()
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack friends() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setDisplayName(Utils.color("&aFriends"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7View your friends, and",
                "&7interact with them!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack recentPlayers() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setDisplayName(Utils.color("&aRecent Players"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7View players you have recently",
                "&7played with!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack party() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setDisplayName(Utils.color("&aParty"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Create a party and join up",
                "&7with other players!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack guild() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setDisplayName(Utils.color("&aGuild"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Create a guild or find one",
                "&7to complete the various",
                "&7obstacles and challenges the",
                "&7server has to offer.!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack boosters() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aBoosters"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Activate any personal and network",
                "&7boosters for additional rewards!",
                "",
                "&eClick to view your boosters!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack character(User user) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        Player player = Bukkit.getPlayer(user.getUuid());
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setOwningPlayer(player);
        meta.setDisplayName(Utils.color("&aCharacter"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Rank: &d" + user.getRank(),
                "&7Level: &d" + Utils.formatAmount(user.getLevel()),
                "&7Experience until Next Level: &d" + Utils.formatAmount(Utils.getExperienceRequiredForLevel(user.getLevel() + 1) - user.getExperience()),
                "&7Achievements Completed: &d" + Utils.formatAmount(user.getAchievements().stream().filter(Achievement::isCompleted).count()),
                "&7Quests Completed: &d" + Utils.formatAmount(user.getQuests().stream().filter(Quest::isCompleted).count()),
                "&7Raven Coins: &d" + Utils.formatAmount(user.getRaven_coins()),
                "&7Shadow Essence: &d" + Utils.formatAmount(user.getShadow_essence())
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack stats() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.color("&aStats"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7See all of your stats for each",
                "&7game and see your overall stats!",
                "",
                "&7Players with &dMVP &7or higher",
                "&7can use /stats (username) to",
                "&7view other players stats!",
                "",
                "&eClick to view your stats!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack achievements() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.color("&aAchievements"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Track your progress as you unlock",
                "&7achievements and earn rewards!",
                "",
                "&eClick to view your achievements!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack level(User user) {
        ItemStack item = new ItemStack(Material.BREWING_STAND);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.color("&aLevel Progress"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Playing games and completing quests",
                "&7will reward you with &dexperience&7,",
                "&7which is required to level up and",
                "&7unlock new features!",
                "",
                "&7Current Level &d" + Utils.formatAmount(user.getLevel()) + " " + experienceBar(user.getLevel(), user.getExperience()) + " &d" + getPercentageComplete(user.getLevel(), user.getExperience()) + "%",
                "&7Next Level &d" + Utils.formatAmount(Utils.getExperienceRequiredForLevel(user.getLevel() + 1) - user.getExperience()),
                "",
                "&eClick to see your rewards!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack quests() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aQuests"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7View your current quests, and",
                "&7complete them for rewards!",
                "",
                "&eClick to view your quests!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack appearance() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.color("&aAppearance"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Change your appearance and",
                "&7show off to other players!",
                "",
                "&eClick to change your appearance!"
        )));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack recentGames() {
        ItemStack item = new ItemStack(Material.CLOCK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Utils.color("&aRecent Games"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7View your recent games and",
                "&7stats for each game!",
                "",
                "&eClick to view!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    private static String experienceBar(int level, int experience) {
        StringBuilder bar = new StringBuilder();
        int experienceRequired = Utils.getExperienceRequiredForLevel(level + 1);
        double percentage = ((double) experience / experienceRequired) * 100;
        int length = (int) ((percentage / 100) * 40);

        bar.append("&a|".repeat(Math.max(0, length)));
        bar.append("&7|".repeat(Math.max(0, 40 - length)));
        return bar.toString();
    }

    private static int getPercentageComplete(int level, int experience) {
        int experienceRequired = Utils.getExperienceRequiredForLevel(level + 1);
        return (int) (((double) experience / experienceRequired) * 100);
    }

}
