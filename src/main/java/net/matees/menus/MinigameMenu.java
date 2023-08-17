package net.matees.menus;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import net.matees.Arcade;
import net.matees.Minigame;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {
        Arcade plugin = Arcade.getPlugin();
        List<Minigame> minigames = plugin.getMinigames();
        Minigame minigame = plugin.getCurrentGame();

        // Get minigame
        for(Minigame game : minigames) {
            if(!game.getMinigameMenuItem().isSimilar(inventoryClickEvent.getCurrentItem())) continue;

            minigame = game;
        }

        // Open settings menu for game
        if(inventoryClickEvent.isRightClick()){
            MenuManager.openMenu(minigame.getSettingsMenu(), (Player) inventoryClickEvent.getWhoClicked());
        }
        
        // Start game
        if(!inventoryClickEvent.isLeftClick()) return;
        plugin.setCurrentGame(minigame);
        minigame.startMinigame();

        for(Listener listener : minigame.getListeners()) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }

        inventoryClickEvent.getInventory().close();
    }

    @Override
    public void setMenuItems() {
        List<Minigame> minigames = Arcade.getPlugin().getMinigames();
        for (int i = 0; i < minigames.size(); i++) {
            Minigame minigame = minigames.get(i);
            inventory.setItem(i, minigame.getMinigameMenuItem());
        }
    }
}