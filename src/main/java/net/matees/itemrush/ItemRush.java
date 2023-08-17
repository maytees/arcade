package net.matees.itemrush;

import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.Menu;
import net.matees.Minigame;
import net.matees.MinigameType;
import net.matees.settings.Setting;
import net.matees.itemrush.listeners.BlockBreak;
import net.matees.itemrush.settings.MaxItemCount;
import net.matees.itemrush.settings.RandomItemCount;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemRush extends Minigame {

    private static final ItemRush INSTANCE = new ItemRush();

    private ItemRush() {
    }

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
                new BlockBreak());
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
                MaxItemCount.getInstance(),
                RandomItemCount.getInstance());
    }

    @Override
    public void startMinigame() {
        Bukkit.broadcastMessage("§aItem Rush has started!");
    }

    @Override
    public ItemStack getMinigameMenuItem() {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aItem Rush");
        meta.setLore(List.of(
                "§7Break blocks to get items!",
                "§7Get lucky!"));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Class<? extends Menu> getSettingsMenu() {
        return ItemRushSettingsMenu.class;
    }

}
