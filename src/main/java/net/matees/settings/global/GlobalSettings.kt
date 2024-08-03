package net.matees.settings.global

import net.matees.settings.AbstractSetting
import net.matees.settings.global.listeners.PlayerJoinListener
import net.matees.settings.global.listeners.PortalTravelEvent
import org.bukkit.event.Listener

class GlobalSettings private constructor() {
    val settings: List<AbstractSetting<*>> = listOf<AbstractSetting<*>>(
        EnableWorldBorder.instance,
        WorldBorderSize.instance,
        FlightEnabled.instance,
        EnableNetherPortal.instance,
        PVPEnabled.instance,
        // TestPlayerBooleanSetting.instance
    )

    fun getSetting(name: String): AbstractSetting<*>? {
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
