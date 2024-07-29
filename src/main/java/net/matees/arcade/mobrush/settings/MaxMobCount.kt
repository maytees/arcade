package net.matees.arcade.mobrush.settings;

import org.bukkit.Material;

import net.matees.settings.IntegerSetting;

public class MaxMobCount extends IntegerSetting {
    private Integer maxMobCount = 16;
    private static final MaxMobCount INSTANCE = new MaxMobCount();

    private MaxMobCount() {
    }

    public static MaxMobCount getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Max Mob Count";
    }

    @Override
    public String getDescription() {
        return "Max amount of mobs which spawn (if random is enabled, this is the range)";
    }

    @Override
    public Integer getSetting() {
        return maxMobCount;
    }

    @Override
    public void setSetting(Integer setting) {
        this.maxMobCount = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.ZOMBIE_HEAD;
    }

    @Override
    public int getMenuItemSlot() {
        return 12;
    }

}
