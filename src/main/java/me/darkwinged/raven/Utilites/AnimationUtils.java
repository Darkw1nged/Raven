package me.darkwinged.raven.Utilites;

import me.darkwinged.raven.Raven;
import me.darkwinged.raven.Utilites.Tasks.MythicCrateEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class AnimationUtils {

    private static final Raven plugin = Raven.getInstance;

    public static void spawnCrate(Location location, float face) {
        ArmorStand crate = location.getWorld().spawn(location, ArmorStand.class);
        crate.setGravity(false);
        crate.setVisible(false);
        crate.setInvulnerable(true);
        crate.setCustomName("crate");
        crate.setCustomNameVisible(false);

        crate.setRotation(face, 0);

        ItemStack item = new ItemStack(Material.ENDER_CHEST);
        crate.setHelmet(item);

        DataCache.crates.put(crate, location);
        Location centerLocation = crate.getLocation();
        double radius = 1;
        int heightOffset = 0;
        int angle = 15;
        Bukkit.getScheduler().runTaskLater(plugin, new MythicCrateEffect(crate, plugin, centerLocation, radius, heightOffset, angle), 1);
    }

}
