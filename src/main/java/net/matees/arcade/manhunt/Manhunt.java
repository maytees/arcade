package net.matees.arcade.manhunt;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.Menu;
import net.matees.Arcade;
import net.matees.Minigame;
import net.matees.MinigameType;
import net.matees.settings.Setting;

public class Manhunt extends Minigame {

    private static final Manhunt INSTANCE = new Manhunt();

    private Manhunt() {
    }

    public static Manhunt getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Manhunt";
    }

    @Override
    public MinigameType getMinigameType() {
        return MinigameType.Manhunt;
    }

    @Override
    public List<Listener> getListeners() {
        return List.of();
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
        return List.of();
    }

    @Override
    public Class<? extends Menu> getSettingsMenu() {
        return ManhuntSettingsMenu.class;
    }

    @Override
    public ItemStack getMinigameMenuItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorTranslator.translateColorCodes("&aManhunt"));
        meta.setLore(List.of(
                ColorTranslator.translateColorCodes("&7Hunt down the runner(s)!"),
                ColorTranslator.translateColorCodes(
                        "&7The runner(s) must kill the ender dragon while hunters attempt to kill the runner(s)"),
                ColorTranslator.translateColorCodes("&3 Right Click to open settings")));

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void doStartMinigame() {
        Bukkit.broadcastMessage("&aManhunt has started!");
        for (Listener listener : this.getListeners()) {
            Arcade.getPlugin().getServer().getPluginManager().registerEvents(listener, Arcade.getPlugin());
        }
    }

    @Override
    public void onStopMinigame() {

    }

}
