package me.darkwinged.raven.Utilites.Tasks;

import lombok.AllArgsConstructor;
import me.darkwinged.raven.Raven;
import me.darkwinged.raven.Utilites.DataCache;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;

@AllArgsConstructor
public class MythicCrateEffect implements Runnable {

    private Raven plugin;
    private ArmorStand crate;
    private Location centerLocation; // The center location of the block
    private double radius; // The radius of the spiral motion
    private int heightOffset; // The offset above the block where the crate will be moved
    private int angle; // The initial angle for the spiral motion

    private double y = 0;
    private double x = 0;
    private double z = 0;

    public MythicCrateEffect(ArmorStand crate, Raven plugin, Location centerLocation, double radius, int heightOffset, int angle) {
        this.plugin = plugin;
        this.crate = crate;
        this.centerLocation = centerLocation;
        this.radius = radius;
        this.heightOffset = heightOffset;
        this.angle = angle;
    }

    public void run() {
        World world = crate.getWorld();

        // Calculate the target location for the next step in the spiral motion
        x = (radius * Math.cos(Math.toRadians(angle))) + .2;
        z = (radius * Math.sin(Math.toRadians(angle)));
        Location targetLocation = centerLocation.clone().add(x, y, z);

        // Move the crate to the target location
        crate.teleport(targetLocation.add(0, heightOffset, 0));

        // Spawn a particle effect trail behind the crate
        world.spawnParticle(Particle.VILLAGER_HAPPY, crate.getLocation().add(0, 0.8, 0), 5, 0.1, 0.1, 0.1, 0.1);

        // Increase the angle for the next step in the spiral motion
        angle += 15;
        y += 0.1;
        // move x and z inwards for the next step in the spiral motion
        radius -= 0.05;

        // Stop the effect when the crate reaches the center location
        if (DataCache.crates.containsKey(crate) && DataCache.crates.get(crate).getY() + y >= DataCache.crates.get(crate).getY() + 3) {
            // Perform any necessary actions when the effect is complete
            // For example, you could remove the ArmorStand or trigger other events

            crate.getWorld().playSound(crate.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, .75f, .75f);
            crate.getWorld().spawnParticle(Particle.CLOUD, crate.getLocation().add(0, .5, 0), 20, 0.5, 0.5, 0.5, 0.1);
            crate.getWorld().spawnParticle(Particle.FLAME, crate.getLocation().add(0, .5, 0), 10, 0.5, 0.5, 0.5, 0.1);

            DataCache.crates.remove(crate);
            crate.remove();
            return;
        }

        // Schedule the next iteration of the effect after a delay (in ticks)
        Bukkit.getScheduler().runTaskLater(plugin, this, 1); // Change 'plugin' to your plugin instance
    }

}
