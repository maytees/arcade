package net.matees.settings.global.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.matees.settings.global.GlobalSettings;

public class PlayerHitEvent implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        boolean setting = (boolean) GlobalSettings.getInstance().getSetting("PVP Enabled").getSetting();
        if (!setting)
            return;

        if (event.getEntityType() != EntityType.PLAYER && event.getDamager().getType() != EntityType.PLAYER)
            return;

        event.setCancelled(true);
    }
}
