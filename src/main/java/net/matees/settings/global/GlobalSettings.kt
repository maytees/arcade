package net.matees.settings.global

import net.matees.settings.*
import net.matees.settings.global.listeners.PlayerJoinListener
import net.matees.settings.global.listeners.PortalTravelEvent
import org.bukkit.event.Listener

class GlobalSettings private constructor() {
    val settings: List<Setting<*>> = listOf<Setting<*>>(
        EnableWorldBorder.instance,
        WorldBorderSize.instance,
        FlightEnabled.instance,
        EnableNetherPortal.instance,
        PVPEnabled.instance
    )

    fun getSetting(name: String): Setting<*>? {
        for (setting in this.settings) {
            if (setting.name == name) {
                return setting
            }
        }

        return null
    }

    val listeners: List<Listener>
        get() = listOf(
            PlayerJoinListener(),
            PortalTravelEvent()
        )

    companion object {
        val instance: GlobalSettings = GlobalSettings()
    }
}
