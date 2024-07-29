package net.matees.settings.global;

import java.util.List;

import org.bukkit.event.Listener;

import net.matees.settings.Setting;
import net.matees.settings.global.listeners.PlayerJoinListener;
import net.matees.settings.global.listeners.PortalTravelEvent;

public class GlobalSettings {
    private final static GlobalSettings INSTANCE = new GlobalSettings();
    private List<Setting> settings = List.of(
            EnableWorldBorder.getInstance(),
            WorldBorderSize.getInstance(),
            FlightEnabled.getInstance(),
            EnableNetherPortal.getInstance(),
            PVPEnabled.getInstance());

    private GlobalSettings() {
    }

    public static GlobalSettings getInstance() {
        return INSTANCE;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public Setting getSetting(String name) {
        for (Setting setting : this.getSettings()) {
            if (setting.getName().equals(name)) {
                return setting;
            }
        }

        return null;
    }

    public List<Listener> getListeners() {
        return List.of(
                new PlayerJoinListener(),
                new PortalTravelEvent());
    }
}
