package me.darkwinged.raven.Utilites.Tasks;

import me.darkwinged.raven.Stuts.User;
import me.darkwinged.raven.Utilites.DataCache;
import me.darkwinged.raven.Utilites.RavenAPI;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class OfflinePlayerSave implements Runnable {

    @Override
    public void run() {
        long currentMillis = System.currentTimeMillis();

        Iterator<Map.Entry<UUID, User>> iterator = DataCache.users.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, User> entry = iterator.next();
            if (Bukkit.getOfflinePlayer(entry.getKey()).isOnline()) continue;

            User user = entry.getValue();
            Instant lastSeenInstant = user.getLast_seen().atZone(ZoneId.systemDefault()).toInstant();
            long offlineTimeMillis = Duration.between(lastSeenInstant, Instant.now()).toMillis();

            if (offlineTimeMillis >= 2 * 60 * 60 * 1000) { // Two hours in milliseconds
                iterator.remove(); // Safely remove the user from the map
                RavenAPI.saveUser(user); // Save the user to the database
            }
        }
    }

}
