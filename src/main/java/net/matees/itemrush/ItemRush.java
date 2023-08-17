package net.matees.itemrush;

import me.kodysimpson.simpapi.command.SubCommand;
import net.matees.Minigame;
import net.matees.MinigameType;
import net.matees.Setting;
import net.matees.itemrush.listeners.BlockBreak;
import net.matees.itemrush.settings.MaxItemCount;
import net.matees.itemrush.settings.RandomItemCount;
import org.bukkit.event.Listener;

import java.util.List;

public class ItemRush extends Minigame {

    private static final ItemRush INSTANCE = new ItemRush();

    private ItemRush() { }

    public static ItemRush getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Item Rush";
    }

    @Override
    public MinigameType getMinigameType() {
        return MinigameType.ItemRush;
    }

    @Override
    public List<Listener> getListeners() {
        return List.of(
                new BlockBreak()
        );
    }

    @Override
    public List<Class<? extends SubCommand>> getSubCommands() {
        return null;
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public String getCommandDescription() {
        return null;
    }

    @Override
    public String getCommandUsage() {
        return null;
    }

    @Override
    public List<Setting> getSettings() {
        return List.of(
                new MaxItemCount(),
                new RandomItemCount()
        );
    }
}
