package net.matees.settings;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.Minigame;
import net.matees.arcade.itemrush.ItemRush;

public abstract class SettingsMenu extends Menu {

    public SettingsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public abstract Minigame getMinigame();

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent)
            throws MenuManagerNotSetupException, MenuManagerException {
        for (Setting setting : this.getMinigame().getSettings()) {
            if (setting.getMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) {
                setting.handleItemClick(inventoryClickEvent);

                // Updates?
                inventory.clear();
                setting.setMenuItem(setting.getMenuItem());
                this.setMenuItems();
            }
        }
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void setMenuItems() {
        for (Setting setting : this.getMinigame().getSettings()) {
            inventory.setItem(setting.getMenuItemSlot(), setting.getMenuItem());
        }
    }
}
