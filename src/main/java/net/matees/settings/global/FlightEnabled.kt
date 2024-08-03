package net.matees.settings.global

import net.matees.settings.AbstractBooleanSetting
import net.matees.settings.IGlobal
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player

class FlightEnabled private constructor() : AbstractBooleanSetting(), IGlobal {
    override var setting: Boolean? = false

    override fun onChange() {
        Bukkit.getOnlinePlayers().forEach { player: Player ->
            if (player.gameMode == GameMode.CREATIVE) return@forEach
            player.allowFlight = setting == true
        }
    }

    override val name: String
        get() = "Enable Flight"

    override val description: String
        get() = "Enables flight for all players"

    override val menuItemMaterial: Material
        get() = Material.PHANTOM_MEMBRANE

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: FlightEnabled = FlightEnabled()
    }
}
