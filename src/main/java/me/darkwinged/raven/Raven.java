package me.darkwinged.raven;

import me.darkwinged.raven.Commands.*;
import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.RavenAPI;
import me.darkwinged.raven.Utilites.Storage.SQLTables;
import me.darkwinged.raven.Utilites.Storage.SQLibrary;
import me.darkwinged.raven.Utilites.Tasks.OfflinePlayerSave;
import me.darkwinged.raven.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDateTime;
import java.util.*;

public final class Raven extends JavaPlugin {

    public static Raven getInstance;

    public static SQLibrary sqlLibrary;
    public static long serverStartTime;

    // Tasks
    private static BukkitTask offlinePlayerSave;

    public void onEnable() {
        getInstance = this;
        serverStartTime = System.currentTimeMillis();

        connectToDatabase();
        SQLTables.load();

        loadCommands();
        loadEvents();

        offlinePlayerSave = Bukkit.getScheduler().runTaskTimer(this, new OfflinePlayerSave(), 0L, 20L * 60L);
    }

    public void onDisable() {
         offlinePlayerSave.cancel();
         saveUsers();
    }

    private void loadCommands() {
        getCommand("cache").setExecutor(new cacheCommand());
        getCommand("rank").setExecutor(new rankCommand());
        getCommand("block").setExecutor(new blockCommand());
        getCommand("unblock").setExecutor(new unblockCommand());
        getCommand("friend").setExecutor(new friendCommand());
    }

    private void loadEvents() {
        Arrays.asList(
                new loadUser(),
                new forceSlot(),
                new disableEvents(),
                new mythicCache()
                ,new tester()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }

    private void connectToDatabase() {
        sqlLibrary = new SQLibrary("localhost", 3306, "raven_realms", "root", "99Bootboy!");

        if (sqlLibrary.getConnection() == null) {
            System.out.println("Unable to establish a connection to MySQL.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void saveUsers() {
        Iterator<Map.Entry<UUID, User>> iterator = DataCache.users.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, User> entry = iterator.next();

            User user = entry.getValue();
            user.setLast_seen(LocalDateTime.now()); // Set the last seen time to now
            iterator.remove(); // Safely remove the user from the map
            RavenAPI.saveUser(user); // Save the user to the database
        }
    }

}
