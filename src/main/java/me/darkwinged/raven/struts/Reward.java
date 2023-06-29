package me.darkwinged.raven.struts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@AllArgsConstructor
public class Reward {

    @Getter
    private final UUID uuid;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private ItemStack item;
    @Getter @Setter
    private int itemAmount;
    @Getter @Setter
    private int coins;
    @Getter @Setter
    private int experience;

}
