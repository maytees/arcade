package net.matees.settings.global;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import net.matees.settings.BooleanSetting;
import net.matees.settings.Global;

public class FlightEnabled extends BooleanSetting implements Global {

    private static final FlightEnabled INSTANCE = new FlightEnabled();
    private Boolean setting = false;

    private FlightEnabled() {
    }

    public static FlightEnabled getInstance() {
        return INSTANCE;
    }

    @Override
    public void onChange() {
        Bukkit.getOnlinePlayers().forEach(player -> player.setAllowFlight(this.getSetting()));
    }

    @Override
    public String getName() {
        return "Enable Flight";
    }

    @Override
    public String getDescription() {
        return "Enables flight for all players";
    }

    @Override
    public Boolean getSetting() {
        return this.setting;
    }

    @Override
    public void setSetting(Boolean setting) {
        this.setting = setting;
    }

    @Override
    public Material getMenuItemMaterial() {
        return Material.PHANTOM_MEMBRANE;
    }

    @Override
    public int getMenuItemSlot() {
        return 12;
    }

}
