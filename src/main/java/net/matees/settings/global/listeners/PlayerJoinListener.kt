package net.matees.settings.global.listeners

import net.matees.settings.global.GlobalSettings
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.allowFlight =
            GlobalSettings.Companion.instance.getSetting("Enable Flight")?.setting as Boolean
        if (event.player.gameMode == GameMode.CREATIVE) event.player.allowFlight = true
    }
}
