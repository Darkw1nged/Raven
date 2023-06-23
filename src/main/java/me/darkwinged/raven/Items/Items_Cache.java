package me.darkwinged.raven.Items;

import me.darkwinged.raven.Raven;
import me.darkwinged.raven.Stuts.CacheReward;
import me.darkwinged.raven.Stuts.Enums.Ranks;
import me.darkwinged.raven.Stuts.MilestoneReward;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items_Cache {

    // Cache Inventory
    public static ItemStack create(int rating, LocalDateTime expiresOn, List<CacheReward> rewards) {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&dMystic Cache"));

        StringBuilder rewardList = new StringBuilder();
        for (CacheReward reward : rewards) {
            rewardList.append("\n").append(Utils.color(reward.getName()));
        }

        String quality = "&e⭐".repeat(Math.max(0, rating)) + "&7⭐".repeat(Math.max(0, 5 - rating));

        List<String> lore = new ArrayList<>();
        lore.add("&7This cache contains one of the following:");
        lore.addAll(Arrays.asList(rewardList.toString().split("\n")));
        lore.add("");
        lore.add("&7Quality: " + quality);
        if (expiresOn != null) {
            lore.add("&cExpires: " + Utils.convertDate(expiresOn));
        }
        lore.add("");
        lore.add("&eClick to open!");

        meta.setLore(Utils.color(lore));

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack noMysticCachesFound() {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&cOh no!"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7You don't have any &dMystic Caches!",
                "&7You can obtain &dMystic Caches &7by",
                "&7playing games, crafting, or purchasing,",
                "&7them on out store at:",
                "&estore.ravensrealm.net"
        )));

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack craftCaches(User user) {
        ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aCraft Mystic Caches"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Whenever you find items that you",
                "&7already have, you will receive",
                "&7&dShadow Essence &7instead. You can",
                "&7use that essence to craft unique",
                "&7Mystic Caches with exclusive loot!",
                "",
                "&7You have &d" + user.getShadow_essence() + " Shadow Essence",
                "&eClick to view Crafting options!"

        )));

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack information() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aMythic Cache Information"));
        meta.setLore(Utils.color(Arrays.asList(
                "&dMystic Caches &7can contain",
                "&7various cosmetics.",
                "&7To earn &dShadow Essence&7, you can",
                "&7play games on the server, complete",
                "&7quests and achievements!"
        )));

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack openMulti(User user) {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();

        if (user.getRank().equals(Ranks.MVP_PLUS_PLUS.getName()) || user.getRank().equals(Ranks.ADMIN.getName()) || user.getRank().equals(Ranks.OWNER.getName())) {
            meta.setDisplayName(Utils.color("&aOpen Multiple Caches"));
            meta.setLore(Utils.color(Arrays.asList(
                    "&7You can open multiple caches at once!",
                    "",
                    "&eClick to open multiple caches!"
            )));
        } else {
            meta.setDisplayName(Utils.color("&cOpen Multiple Caches"));
            meta.setLore(Utils.color(Arrays.asList(
                    "&7You can open multiple caches at once!",
                    "",
                    "&cRequires &dMVP++"
            )));
        }

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack gift(User user) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aGift Inventory"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Send gifts to your friends and",
                "&7claim unique rewards in return!",
                "",
                "&7Purchase gifts on our store at:",
                "&estore.ravensrealm.net",
                "",
                "&7Gifts send: &d" + user.getCachesGifted(),
                "&7Gifts received: &d" + user.getCachesReceived(),
                "",
                "&eClick to view your Gift Inventory!"
        )));

        item.setItemMeta(meta);
        return item;
    }

    // Sub Inventory Items
    public static ItemStack back() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aGo Back"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7To the previous menu"
        )));

        item.setItemMeta(meta);
        return item;
    }

    // Cache Gift Inventory
    public static ItemStack contributor(User user) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        Player player = Bukkit.getPlayer(user.getUuid());

        meta.setOwningPlayer(player);
        meta.setDisplayName(Utils.color("&aGift Contributor Rewards"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7Sending gifts to your friends",
                "&7allows you to cloam unique and",
                "&7exclusive rewards!",
                "",
                "&7Purchase gifts on our store at:",
                "&estore.ravensrealm.net",
                "",
                "&7Gifts sent: &d" + user.getCachesGifted()
        )));

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack milestone(User user, int milestone, MilestoneReward rewards) {
        ItemStack item = new ItemStack(user.getCachesGifted() >= milestone ? Material.GREEN_CONCRETE : Material.RED_CONCRETE);
        ItemMeta meta = item.getItemMeta();

        if (milestone == 1) {
            meta.setDisplayName(Utils.color("&a" + milestone + " Gift Milestone Reward"));
        } else if (milestone == 5) {
            meta.setDisplayName(Utils.color((user.getCachesGifted() >= 1 ? "&a" : "&c") + milestone + " Gift Milestone Reward"));
        } else if (milestone == 10) {
            meta.setDisplayName(Utils.color((user.getCachesGifted() >= 5 ? "&a" : "&c") + milestone + " Gift Milestone Reward"));
        } else if (milestone == 25) {
            meta.setDisplayName(Utils.color((user.getCachesGifted() >= 10 ? "&a" : "&c") + milestone + " Gift Milestone Reward"));
        } else if (milestone == 50) {
            meta.setDisplayName(Utils.color((user.getCachesGifted() >= 25 ? "&a" : "&c") + milestone + " Gift Milestone Reward"));
        } else if (milestone == 100) {
            meta.setDisplayName(Utils.color((user.getCachesGifted() >= 50 ? "&a" : "&c") + milestone + " Gift Milestone Reward"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String reward : rewards.getRewards()) {
            lore.add(Utils.color(reward));
        }
        lore.add("");
        if (user.getCachesGifted() >= milestone) {
            lore.add(Utils.color("&eClick to claim!"));
        } else {
            lore.add(Utils.color("&cYou need to send &a" + (milestone - user.getCachesGifted()) + " &cmore gifts"));
            lore.add(Utils.color("&cto claim this reward!"));
        }
        meta.setLore(lore);

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(Raven.getInstance, "milestone"), PersistentDataType.INTEGER, milestone);

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack giftBox(int amount) {
        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aGift Box (" + amount + "-Pack)"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7This gift pack contains &a" + amount,
                "&dMystic Caches&7!",
                "",
                "&eClick to send to a friend!"
        )));

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(Raven.getInstance, "gift-pack"), PersistentDataType.INTEGER, amount);

        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack noGiftPacksFound() {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&cOh no!"));
        meta.setLore(Utils.color(Arrays.asList(
                "&7It looks like you don't have any",
                "&7gift packs to send to your friends!",
                "",
                "&7Purchase gifts on our store at:",
                "&estore.ravensrealm.net"
        )));

        item.setItemMeta(meta);
        return item;
    }

}
