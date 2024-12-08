package net.matees.settings.global.listeners

import net.matees.settings.global.GlobalSettings
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPortalEvent

class PortalTravelEvent : Listener {
    @EventHandler
    fun onPortalTravel(event: PlayerPortalEvent) {
        val setting = GlobalSettings.Companion.instance.getSetting("Enable Nether Portal")?.setting as Boolean
        if (!setting) event.isCancelled = true
    }
}
