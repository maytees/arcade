package net.matees.settings.global.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import net.matees.settings.global.GlobalSettings;

public class PortalTravelEvent implements Listener {
    @EventHandler
    public void onPortalTravel(PlayerPortalEvent event) {
        boolean setting = (boolean) GlobalSettings.getInstance().getSetting("Enable Nether Portal").getSetting();
        if (!setting)
            event.setCancelled(true);
    }
}
