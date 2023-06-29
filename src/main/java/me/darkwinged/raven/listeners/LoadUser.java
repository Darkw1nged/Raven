package me.darkwinged.raven.listeners;

import me.darkwinged.raven.items.Items_Hotbar;
import me.darkwinged.raven.menus.CollectiblesMenu;
import me.darkwinged.raven.menus.GamemodeSelectorMenu;
import me.darkwinged.raven.menus.LobbySelectorMenu;
import me.darkwinged.raven.menus.ProfileMenu;
import me.darkwinged.raven.struts.User;
import me.darkwinged.raven.utilites.DataCache;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
import me.darkwinged.raven.utilites.RavenAPI;
import me.darkwinged.raven.utilites.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;

public class LoadUser implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (DataCache.users.containsKey(player.getUniqueId())) {
            setInventory(player, DataCache.users.get(player.getUniqueId()));
            return;
        }

        User user = RavenAPI.getUser(player.getUniqueId());
//        if (user != null) System.out.println(user.toString());

        if (user == null) {
            user = new User(player.getUniqueId(), 0, 0, 0, "Default", 0);
            user.setFirst_join(LocalDateTime.now());
        }

        user.setRecent_join(LocalDateTime.now());
        DataCache.users.put(player.getUniqueId(), user);
        player.setGameMode(GameMode.ADVENTURE);
        setInventory(player, user);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!DataCache.users.containsKey(player.getUniqueId())) return;

        User user = DataCache.users.get(player.getUniqueId());
        user.setLast_seen(LocalDateTime.now());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.getHand().equals(EquipmentSlot.HAND)) return;
        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) return;

        ItemStack item = event.getItem();
        if (item == null) return;

        if (item.equals(Items_Hotbar.PlayerVisibilityEnabled())) {
            user.setPlayerVisibility(false);
            setInventory(player, user);
            player.sendMessage(Utils.color("&aPlayer visibility enabled!"));;
            return;
        } else if (item.equals(Items_Hotbar.PlayerVisibilityDisabled())) {
            user.setPlayerVisibility(true);
            setInventory(player, user);
            player.sendMessage(Utils.color("&cPlayer visibility disabled!"));
            return;
        }

        // TODO - Equipped Cosmetic

        openMenu(player, item);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory inv = event.getClickedInventory();
        if (inv == null) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        if (inv.equals(player.getInventory())) {
            event.setCancelled(true);
            item.setAmount(0);
            openMenu(player, item);
        }
    }

    private void setInventory(Player player, User user) {
        Inventory inv = player.getInventory();

        inv.setItem(0, Items_Hotbar.GamemodeSelector());
        inv.setItem(1, Items_Hotbar.Profile(player));
        // Empty
        // Empty
        inv.setItem(4, Items_Hotbar.Cosmetics(user));
        inv.setItem(5, Items_Hotbar.EquippedCosmetic());
        // Empty
        if (user.isPlayerVisibility()) {
            inv.setItem(7, Items_Hotbar.PlayerVisibilityEnabled());
        } else {
            inv.setItem(7, Items_Hotbar.PlayerVisibilityDisabled());
        }
        inv.setItem(8, Items_Hotbar.LobbySelector());
    }

    private void openMenu(Player player, ItemStack item) {
        if (item.equals(Items_Hotbar.GamemodeSelector())) {
            GamemodeSelectorMenu menu = new GamemodeSelectorMenu(new MenuOwnerUtil(player));
            menu.open();
        } else if (item.equals(Items_Hotbar.Profile(player))) {
            ProfileMenu menu = new ProfileMenu(new MenuOwnerUtil(player));
            menu.open();
        } else if (item.equals(Items_Hotbar.Cosmetics(DataCache.users.get(player.getUniqueId())))) {
            CollectiblesMenu menu = new CollectiblesMenu(new MenuOwnerUtil(player));
            menu.open();
        }  else if (item.equals(Items_Hotbar.LobbySelector())) {
            LobbySelectorMenu menu = new LobbySelectorMenu(new MenuOwnerUtil(player));
            menu.open();
        }
    }

}
