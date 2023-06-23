package me.darkwinged.raven.Utilites;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    public static int getExpToLevelUp(int level) {
        return (level <= 15) ? (2 * level + 7) : ((level <= 30) ? (5 * level - 38) : (9 * level - 158));
    }

    public static int getExpAtLevel(int level) {
        return (level <= 16) ? (int)(Math.pow(level, 2.0D) + (6 * level)) : ((level <= 31) ? (int)(2.5D * Math.pow(level, 2.0D) - 40.5D * level + 360.0D) : (int)(4.5D * Math.pow(level, 2.0D) - 162.5D * level + 2220.0D));
    }

    public static int getPlayerExp(Player player) {
        int exp = 0;
        int level = player.getLevel();
        exp += getExpAtLevel(level);
        exp += Math.round(getExpToLevelUp(level) * player.getExp());
        return exp;
    }

    public static void removePlayerExp(Player player, int exp) {
        int currentExp = getPlayerExp(player);
        player.setExp(0.0F);
        player.setLevel(0);
        int newExp = currentExp - exp;
        player.giveExp(newExp);
    }

    public static void addPlayerExp(Player player, int exp) {
        int currentExp = getPlayerExp(player);
        player.setExp(0.0F);
        player.setLevel(0);
        int newExp = currentExp + exp;
        player.giveExp(newExp);
    }

    public static void setPlayerExp(Player player, int exp) {
        player.setExp(0.0F);
        player.setLevel(0);
        player.giveExp(exp);
    }

    // Check if the player has enough space to add a new item.
    public static boolean hasSpace(Player player, ItemStack targetItem, int amount) {
        // Getting the players inventory.
        Inventory inventory = player.getInventory();
        // Looping through the players inventory.
        for (int i = 0; i < inventory.getSize(); i++) {
            // Check if i == 36, if so, break the loop.
            if (i == 36) break;
            // Checking if the item is null.
            if (inventory.getItem(i) == null) {
                // Adding the item to the inventory.
                inventory.setItem(i, targetItem);
                // Mining 1 from the amount.
                amount--;
                // Checking if the amount is greater than 0.
                if (amount > 0) {
                    // Set the amount to either amount + 1 or max stack size.
                    inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() + Math.min(amount, (targetItem.getMaxStackSize() - inventory.getItem(i).getAmount())));
                    // Updating the amount.
                    amount -= (inventory.getItem(i).getAmount() - 1);
                    // If amount is greater than 0, continue the loop.
                    if (amount > 0) {
                        continue;
                    }
                }
                // return true if the amount is 0.
                return true;
            } else {
                // Checking if the item type is the same as the target item type.
                if (inventory.getItem(i).getType() == targetItem.getType()) {
                    // Checking if both items have the same meta data.
                    if (inventory.getItem(i).hasItemMeta() && targetItem.hasItemMeta()) {
                        if (inventory.getItem(i).getItemMeta().getDisplayName().equals(targetItem.getItemMeta().getDisplayName()) &&
                                inventory.getItem(i).getItemMeta().getLore().equals(targetItem.getItemMeta().getLore())) {
                            // Check if the item is at max stack size.
                            if (inventory.getItem(i).getAmount() == inventory.getItem(i).getMaxStackSize()) continue;
                            // Getting the amount of the item.
                            int itemAmountBefore = inventory.getItem(i).getAmount();
                            // Updating item amount.
                            inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() + Math.min(amount, (targetItem.getMaxStackSize() - inventory.getItem(i).getAmount())));
                            // Updating the amount.
                            amount -= (inventory.getItem(i).getAmount() - itemAmountBefore);
                            // If amount is greater than 0, continue the loop.
                            if (amount > 0) {
                                continue;
                            }
                            // return true if the amount is 0.
                            return true;
                        }
                    } else {
                        /// Check if the item is at max stack size.
                        if (inventory.getItem(i).getAmount() == inventory.getItem(i).getMaxStackSize()) continue;
                        // Getting the amount of the item.
                        int itemAmountBefore = inventory.getItem(i).getAmount();
                        // Updating item amount.
                        inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() + Math.min(amount, (targetItem.getMaxStackSize() - inventory.getItem(i).getAmount())));
                        // Updating the amount.
                        amount -= (inventory.getItem(i).getAmount() - itemAmountBefore);
                        // If amount is greater than 0, continue the loop.
                        if (amount > 0) {
                            continue;
                        }
                        // return true if the amount is 0.
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String getPlayerDirection(Player player) {
        String direction = "W";
        double rotation = ((player.getLocation().getYaw() - 90.0F) % 360.0F);
        if (rotation < 0.0D)
            rotation += 360.0D;
        if (0.0D <= rotation && rotation < 22.5D)
            return "W";
        if (22.5D <= rotation && rotation < 67.5D)
            return "NW";
        if (67.5D <= rotation && rotation < 112.5D)
            return "N";
        if (112.5D <= rotation && rotation < 157.5D)
            return "NE";
        if (157.5D <= rotation && rotation < 202.5D)
            return "E";
        if (202.5D <= rotation && rotation < 247.5D)
            return "SE";
        if (247.5D <= rotation && rotation < 292.5D)
            return "S";
        if (292.5D <= rotation && rotation < 337.5D)
            return "SW";
        if (337.5D <= rotation && rotation < 360.0D)
            return "W";
        return direction;
    }

}
