package me.darkwinged.raven.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import me.darkwinged.raven.Stuts.Enums.Ranks;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class disableEvents implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupExperienceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(EntityPickupItemEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;

        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(disable(user));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(disable(user));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) {
            event.setCancelled(true);
            return;
        }

        if (event.getBlock().getType().equals(Material.ENDER_CHEST)) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(disable(user));
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(disable(user));
    }

    @EventHandler
    public void onDrop(EntityDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void loseHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        HumanEntity player = event.getWhoClicked();
        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(disable(user));
    }

    private boolean disable(User user) {
        return !user.getRank().equals(Ranks.ADMIN.getName()) && !user.getRank().equals(Ranks.OWNER.getName());
    }

}
