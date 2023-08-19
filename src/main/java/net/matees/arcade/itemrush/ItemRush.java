package net.matees.arcade.itemrush;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.Menu;
import net.matees.Arcade;
import net.matees.Minigame;
import net.matees.MinigameType;
import net.matees.arcade.itemrush.listeners.BlockBreak;
import net.matees.arcade.itemrush.settings.MaxItemCount;
import net.matees.arcade.itemrush.settings.RandomItemCount;
import net.matees.settings.Setting;

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
    public void doStartMinigame() {
        Bukkit.broadcastMessage("§aItem Rush has started!");
        for (Listener listener : this.getListeners()) {
            Arcade.getPlugin().getServer().getPluginManager().registerEvents(listener, Arcade.getPlugin());
        }
    }

    @Override
    public ItemStack getMinigameMenuItem() {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aItem Rush");
        meta.setLore(List.of(
                ColorTranslator.translateColorCodes("§7Break blocks to get items!"),
                ColorTranslator.translateColorCodes("§7Get lucky!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")));

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Class<? extends Menu> getSettingsMenu() {
        return ItemRushSettingsMenu.class;
    }

    @Override
    public void onStopMinigame() {

    }

}
