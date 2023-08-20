package net.matees.arcade.lavarise;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.Menu;
import net.matees.Arcade;
import net.matees.arcade.Minigame;
import net.matees.arcade.MinigameType;
import net.matees.settings.Setting;

public class LavaRise extends Minigame {

    private static final LavaRise INSTANCE = new LavaRise();
    private List<Player> playersAlive = new ArrayList<>();
    private static int yCoord = -64;

    private LavaRise() {
    }

    public List<Player> getPlayersAlive() {
        return this.playersAlive;
    }

    public void removePlayer(Player player) {
        this.getPlayersAlive().remove(player);
    }

    public static LavaRise getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Lava Rise";
    }

    @Override
    public MinigameType getMinigameType() {
        return MinigameType.LavaRise;
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
        return LavaRiseSettingsMenu.class;
    }

    @Override
    public ItemStack getMinigameMenuItem() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aLava Rise");
        meta.setLore(List.of(
                ColorTranslator.translateColorCodes("§7Lava rises every minute"),
                ColorTranslator.translateColorCodes("§7Build up and don't die!"),
                ColorTranslator.translateColorCodes("&3Last to die wins.")));
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void doStartMinigame() {
        Bukkit.broadcastMessage("§aLava Rise has started!");
        for (Listener listener : this.getListeners()) {
            Arcade.getPlugin().getServer().getPluginManager().registerEvents(listener, Arcade.getPlugin());
        }

        this.startLavaRise();
    }

    private void startLavaRise() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!INSTANCE.isCurrentMinigame() || yCoord == 322)
                    return;

                World world = Arcade.getPlugin().getServer().getWorld("world");
                int size = (int) world.getWorldBorder().getSize();
                double centerX = world.getWorldBorder().getCenter().getX();
                double centerZ = world.getWorldBorder().getCenter().getZ();

                double halfSize = size / 2;

                for (double x = centerX - halfSize; x <= centerX + halfSize; x++) {
                    for (double z = centerZ - halfSize; z <= centerZ + halfSize; z++) {
                        Block block = world.getBlockAt((int) x, yCoord, (int) z);

                        // Add setting: Only air blocks
                        if (block.getType() == Material.AIR && block.getType() != Material.WATER) {
                            block.setType(Material.LAVA);
                            block.setMetadata("from_arcade", new FixedMetadataValue(Arcade.getPlugin(), true));
                        }
                    }
                }

                yCoord++;
                Bukkit.broadcastMessage("§cRaised lava level to y=" + yCoord);
            }

        }.runTaskTimer(Arcade.getPlugin(), 0L, 1200);
    }

    @Override
    public void onStopMinigame() {

        new BukkitRunnable() {
            @Override
            public void run() {
                if (INSTANCE.isCurrentMinigame() || yCoord == -64)
                    return;

                World world = Arcade.getPlugin().getServer().getWorld("world");
                int size = (int) world.getWorldBorder().getSize();
                double centerX = world.getWorldBorder().getCenter().getX();
                double centerZ = world.getWorldBorder().getCenter().getZ();

                double halfSize = size / 2;

                for (double x = centerX - halfSize; x <= centerX + halfSize; x++) {
                    for (double z = centerZ - halfSize; z <= centerZ + halfSize; z++) {
                        Block block = world.getBlockAt((int) x, yCoord, (int) z);
                        @NotNull
                        List<MetadataValue> values = block.getMetadata("from_arcade");
                        boolean fromArcade = false;
                        for (MetadataValue value : values) {
                            if (value.asBoolean() == true)
                                fromArcade = true;
                        }

                        // Add setting: Only air blocks
                        if (block.getType() == Material.LAVA && fromArcade) {
                            block.setType(Material.AIR);
                        }
                    }
                }

                yCoord--;
                Bukkit.broadcastMessage("§cCurrent y = " + yCoord);
            }

        }.runTaskTimer(Arcade.getPlugin(), 0L, 10);
    }
}
