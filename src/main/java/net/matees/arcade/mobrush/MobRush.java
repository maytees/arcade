package net.matees.arcade.mobrush;

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
import net.matees.arcade.Minigame;
import net.matees.arcade.MinigameType;
import net.matees.arcade.mobrush.listeners.BlockBreak;
import net.matees.arcade.mobrush.settings.EnableHostileMobs;
import net.matees.arcade.mobrush.settings.EnablePeacefulMobs;
import net.matees.arcade.mobrush.settings.MaxMobCount;
import net.matees.arcade.mobrush.settings.RandomMobCount;
import net.matees.settings.Setting;

public class MobRush extends Minigame {

    private static final MobRush INSTANCE = new MobRush();

    private MobRush() {
    }

    public static MobRush getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Mob Rush";
    }

    @Override
    public MinigameType getMinigameType() {
        return MinigameType.MobRush;
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
                MaxMobCount.getInstance(),
                RandomMobCount.getInstance(),
                EnableHostileMobs.getInstance(),
                EnablePeacefulMobs.getInstance());
    }

    @Override
    public Class<? extends Menu> getSettingsMenu() {
        return MobRushSettingsMenu.class;
    }

    @Override
    public ItemStack getMinigameMenuItem() {
        ItemStack item = new ItemStack(Material.CREEPER_HEAD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorTranslator.translateColorCodes("&aMob Rush"));
        meta.setLore(List.of(
                ColorTranslator.translateColorCodes("§7Break blocks to spawn random mobs!"),
                ColorTranslator.translateColorCodes("§7Get lucky!"),
                ColorTranslator.translateColorCodes("&3Right Click to open settings")));

        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void doStartMinigame() {
        Bukkit.broadcastMessage("§aMob Rush has started!");
        for (Listener listener : this.getListeners()) {
            Arcade.getPlugin().getServer().getPluginManager().registerEvents(listener, Arcade.getPlugin());
        }
    }

    @Override
    public void onStopMinigame() {
    }

}
