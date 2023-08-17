package net.matees;

import org.bukkit.plugin.java.JavaPlugin;

public final class Arcade extends JavaPlugin {
    private static Arcade plugin;
    private MinigameType currentGame;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this; 
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Arcade getPlugin() {
        return plugin;
    }

    public MinigameType getCurrentGame() {
        return this.currentGame;
    }

    public void setCurrentGame(MinigameType currentGame) {
        this.currentGame = currentGame;
    }
}
