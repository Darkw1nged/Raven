package me.darkwinged.raven.menus;

import me.darkwinged.raven.struts.GiftPack;
import me.darkwinged.raven.utilites.MenuOwnerUtil;
import me.darkwinged.raven.utilites.PaginatedMenu;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CacheGiftFriendMenu extends PaginatedMenu {

    private final GiftPack giftPack;

    public CacheGiftFriendMenu(MenuOwnerUtil menuOwnerUtil, GiftPack giftPack) {
        super(menuOwnerUtil);
        this.giftPack = giftPack;
    }

    @Override
    public String getMenuName() {
        return "Available Friends";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

    }

    @Override
    public void setMenuItems() {

    }

}
