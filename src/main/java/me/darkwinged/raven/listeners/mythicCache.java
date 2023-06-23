package me.darkwinged.raven.listeners;

import me.darkwinged.raven.Items.Items_Cache;
import me.darkwinged.raven.Menus.CacheGiftMenu;
import me.darkwinged.raven.Menus.CacheMenu;
import me.darkwinged.raven.Stuts.Enums.Ranks;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class mythicCache implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        Block confirmed = block.getLocation().subtract(0, 2, 0).getBlock();

        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) return;

        if (user.getRank().equals(Ranks.ADMIN.getName()) || user.getRank().equals(Ranks.OWNER.getName())) {
            if (block.getType().equals(Material.ENDER_CHEST) && confirmed.getType().equals(Material.END_PORTAL)) {
                if (player.isSneaking() && event.getAction().isLeftClick()) {
                    return;
                }
            }
        }

        if (block.getType().equals(Material.ENDER_CHEST) && confirmed.getType().equals(Material.END_PORTAL)) {
            event.setCancelled(true);
            CacheMenu menu = new CacheMenu(new MenuOwnerUtil(player));
            menu.open();

//            Location location = block.getLocation().add(.3, 0, .5).subtract(0, .5, 0);
//            float face = 180;
//            AnimationUtils.spawnCrate(location, face);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        Block block = event.getBlock();
        if (!block.getType().equals(Material.ENDER_CHEST)) return;
        Block portal = block.getLocation().subtract(0, 2, 0).getBlock();
        if (!portal.getType().equals(Material.END_PORTAL)) return;

        User user = DataCache.users.get(player.getUniqueId());
        if (user == null) return;

        if (user.getRank().equals(Ranks.ADMIN.getName()) || user.getRank().equals(Ranks.OWNER.getName())) {
            if (block.getType().equals(Material.ENDER_CHEST) && portal.getType().equals(Material.END_PORTAL)) {
                block.setType(Material.AIR);
                portal.setType(Material.AIR);
                player.sendMessage(Utils.color("&aYou have broken the Mythic Cache!"));
            }
        }
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event){
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            menu.handleMenu(event);
        }
    }

}
