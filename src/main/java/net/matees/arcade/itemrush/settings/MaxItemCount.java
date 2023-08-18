package net.matees.arcade.itemrush.settings;

import net.matees.settings.IntegerSetting;
import org.bukkit.Material;

public class MaxItemCount extends IntegerSetting {

    private Integer maxItemCount = 16;
    private static final MaxItemCount INSTANCE = new MaxItemCount();

    private MaxItemCount() {
    }

    public static MaxItemCount getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Max Item Count";
    }

    @Override
    public String getDescription() {
        return "Max amount of items dropped (if random is enabled, this is the range)";
    }

    @Override
    public Integer getSetting() {
        return maxItemCount;
    }

    public void setSetting(Integer setting) {
        this.maxItemCount = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.STICK;
    }

    @Override
    public int getMenuItemSlot() {
        return 12;
    }
}
