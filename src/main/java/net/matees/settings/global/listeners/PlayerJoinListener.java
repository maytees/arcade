package net.matees.settings.global.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.matees.settings.global.GlobalSettings;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer()
                .setAllowFlight((boolean) GlobalSettings.getInstance().getSetting("Enable Flight").getSetting());
    }
}
