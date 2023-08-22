package net.matees.arcade.lavarise.settings;

import org.bukkit.Material;

import net.matees.settings.IntegerSetting;

public class TimeToRise extends IntegerSetting {

    private static final TimeToRise INSTANCE = new TimeToRise();
    private Integer setting = 5; // SEconds

    private TimeToRise() {
    }

    public static TimeToRise getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Time To Rise";
    }

    @Override
    public String getDescription() {
        return "Set how often lava rises";
    }

    @Override
    public Integer getSetting() {
        return this.setting;
    }

    @Override
    public void setSetting(Integer setting) {
        this.setting = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.RABBIT_FOOT;
    }

    @Override
    public int getMenuItemSlot() {
        return 15;
    }

}
