package net.matees;

import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.event.Listener;

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

    public Setting getSetting(String name) {
        for(Setting setting : this.getSettings()) {
            if(setting.getName().equals(name)) {
                return setting;
            }
        }

        return null;
    }
}
