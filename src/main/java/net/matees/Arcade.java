package net.matees;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.menu.MenuManager;
import net.matees.arcade.itemrush.ItemRush;
import net.matees.arcade.mobrush.MobRush;
import net.matees.commands.OpenArcadeMenu;

public final class Arcade extends JavaPlugin {
    private static Arcade plugin;
    private Minigame currentGame;

    private List<Minigame> minigames;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        MenuManager.setup(getServer(), this);

        try {
            initalizeCommands();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        setupArcade();
    }

    private void initalizeCommands() throws NoSuchFieldException, IllegalAccessException {
        CommandManager.createCoreCommand(this, "arcade", "Arcade plugin - Survival minigames", "/arcade open|settings",
                null, OpenArcadeMenu.class);
    }

    private void setupArcade() {
        minigames = List.of(
                ItemRush.getInstance(),
                MobRush.getInstance());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Arcade getPlugin() {
        return plugin;
    }

    public Minigame getCurrentGame() {
        return this.currentGame;
    }

    public void setCurrentGame(Minigame currentGame) {
        this.currentGame = currentGame;
    }

    public List<Minigame> getMinigames() {
        return minigames;
    }
}
