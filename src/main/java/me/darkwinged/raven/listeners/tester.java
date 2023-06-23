package me.darkwinged.raven.listeners;

import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class tester implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        User user = DataCache.users.get(player.getUniqueId());
//        System.out.println(user.toString());
        if (action.isRightClick()) {
//            AnimationUtils.spawnCrate(player.getLocation().add(2, 1, 0));
//            player.sendMessage("Spawned");
        }

    }

}
