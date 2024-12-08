package net.matees.settings.global.listeners

import net.matees.settings.global.GlobalSettings
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class PlayerHitEvent : Listener {
    @EventHandler
    fun onPlayerHit(event: EntityDamageByEntityEvent) {
        val setting = GlobalSettings.Companion.instance.getSetting("PVP Enabled")?.setting as Boolean
        if (!setting) return

        if (event.entityType != EntityType.PLAYER && event.damager.type != EntityType.PLAYER) return

        event.isCancelled = true
    }
}
