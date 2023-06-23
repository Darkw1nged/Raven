package me.darkwinged.raven.Items;

import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Items_LobbySelector {

    public static ItemStack full(int amount) {
        ItemStack item = new ItemStack(Material.RED_TERRACOTTA);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&cMain Lobby #" + amount));
        meta.setLore(Arrays.asList(
                Utils.color("&7Players: " + 0 + "/" + 0),
                "",
                Utils.color("&cLobby is full!")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack alreadyConnected(int amount) {
        ItemStack item = new ItemStack(Material.RED_TERRACOTTA);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&cMain Lobby #" + amount));
        meta.setLore(Arrays.asList(
                Utils.color("&7Players: " + 0 + "/" + 0),
                "",
                Utils.color("&cAlready connected!")
        ));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack joinable(User user, int amount) {
        ItemStack item = new ItemStack(Material.WHITE_TERRACOTTA);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.color("&aMain Lobby #" + amount));
        meta.setLore(Arrays.asList(
                Utils.color("&7Players: " + 0 + "/" + 0),
                "",
                // TODO: Check if any friends are in the lobby.
                "",
                Utils.color("&eClick to connect!")
        ));

        item.setItemMeta(meta);
        return item;
    }

}
