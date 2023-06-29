package me.darkwinged.raven.items;

import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Items_Hotbar {

    public static ItemStack GamemodeSelector() {
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aGame Menu &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Right-Click to bring up the Game Menu!")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack Profile(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName(Utils.color("&aProfile &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Right-Click to browse quests, view achievements,"),
                Utils.color("&7activate Boosters, and more!")
        ));
        meta.setOwningPlayer(player);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack EquippedCosmetic() {
        // TODO: Implement
        return null;
    }

    public static ItemStack Cosmetics(User user) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aCollectibles &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Shadow Essence: &d" + Utils.formatAmount(user.getShadowEssence())),
                "",
                Utils.color("&7Collect fun cosmetic items! Get new"),
                Utils.color("&7items by opening &dMystic Cache's &7or"),
                Utils.color("&7hitting milestone rewards, Some can also"),
                Utils.color("&7be crafted with &dShadow Essence&7!"),
                "",
                Utils.color("&dShadow Essence &7can be obtained by"),
                Utils.color("&7opening &dMystic Cache's&7, &dQuests&7,"),
                Utils.color("&7and &dAchievements&7!"),
                "",
                Utils.color("&7You can support Raven's Realm server by"),
                Utils.color("&7buying &dMystic Cache's &7from our store."),
                "",
                Utils.color("&estore.ravensrealm.net")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack PlayerVisibilityEnabled() {
        ItemStack item = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&fPlayers: &cHidden &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Right-Click to toggle player visibility!")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack PlayerVisibilityDisabled() {
        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&fPlayers: &aVisible &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Right-Click to toggle player visibility!")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack LobbySelector() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aLobby Selector &7(Right Click)"));
        meta.setLore(Arrays.asList(
                Utils.color("&7Right-Click to switch between lobbies!")
        ));

        item.setItemMeta(meta);
        return item;
    }



}
