package net.matees.arcade;

import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.Menu;
import net.matees.settings.Setting;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Minigame {
    private boolean isCurrentMinigame;

    public boolean isCurrentMinigame() {
        return this.isCurrentMinigame;
    }

    public void setIsCurrentMinigame(boolean current) {
        this.isCurrentMinigame = current;
    }

    public abstract String getName();

    public abstract MinigameType getMinigameType();

    public abstract List<Listener> getListeners();

    public abstract List<Class<? extends SubCommand>> getSubCommands();

    public abstract String getCommandName();

    public abstract String getCommandDescription();

    public abstract String getCommandUsage();

    public abstract List<Setting> getSettings();

    public abstract Class<? extends Menu> getSettingsMenu();

    public Setting getSetting(String name) {
        for (Setting setting : this.getSettings()) {
            if (setting.getName().equals(name)) {
                return setting;
            }
        }

        return null;
    }

    public abstract ItemStack getMinigameMenuItem();

    public abstract void doStartMinigame();

    public void startMinigame() {
        this.setIsCurrentMinigame(true);
        this.doStartMinigame();
    }

    public void stopMinigame() {
        this.setIsCurrentMinigame(false);
        this.onStopMinigame();
    }

    public abstract void onStopMinigame();

}
