package net.matees.menus;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.Arcade;
import net.matees.Minigame;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MinigameMenu extends Menu {

    public MinigameMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Start Minigame";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent)
            throws MenuManagerNotSetupException, MenuManagerException {
        Arcade plugin = Arcade.getPlugin();
        List<Minigame> minigames = plugin.getMinigames();
        Minigame clicked = null;

        // Stop current minigame
        if (inventoryClickEvent.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
            if (plugin.getCurrentGame() == null) {
                inventoryClickEvent.getWhoClicked().sendMessage("§cThere is no current minigame!");
                inventory.close();
                return;
            }
            plugin.getCurrentGame().stopMinigame();
            plugin.setCurrentGame(null);
            inventoryClickEvent.getWhoClicked().sendMessage("§cStopped current minigame!");
            inventory.close();
            return;
        }

        // If right click, open settings menu
        if (inventoryClickEvent.isRightClick()) {
            for (Minigame game : minigames) {
                if (game.getMinigameMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) {
                    MenuManager.openMenu(game.getSettingsMenu(), (Player) inventoryClickEvent.getWhoClicked());
                    return;
                }
            }
        }

        for (Minigame game : minigames) {
            if (plugin.getCurrentGame() != null)
                if (plugin.getCurrentGame().getMinigameMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) {
                    inventoryClickEvent.getWhoClicked()
                            .sendMessage(ColorTranslator.translateColorCodes("&cThis minigame is already running!"));
                    inventory.close();
                    return;
                }
            ;

            if (game.getMinigameMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) {
                clicked = game;
            }

        }

        if (plugin.getCurrentGame() != null) {
            plugin.getCurrentGame().stopMinigame();
            plugin.setCurrentGame(null);
        }

        plugin.setCurrentGame(clicked);
        clicked.startMinigame();
        inventory.close();

        inventory.close();
    }

    @Override
    public void setMenuItems() {
        List<Minigame> minigames = Arcade.getPlugin().getMinigames();

        for (int i = 0; i < minigames.size(); i++) {
            if (i == 17 || i == 26 || i == 35 || i == 44 || i == 53 || i == 18 || i == 27
                    || i == 36 || i == 45)
                continue;
            Minigame minigame = minigames.get(i);
            inventory.setItem(i + 10, minigame.getMinigameMenuItem());
        }

        ItemStack stopCurrent = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta meta = stopCurrent.getItemMeta();
        meta.setDisplayName("§cStop Current Minigame");
        stopCurrent.setItemMeta(meta);
        inventory.setItem(49, stopCurrent);
    }
}