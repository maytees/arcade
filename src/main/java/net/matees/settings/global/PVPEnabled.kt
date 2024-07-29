package net.matees.settings.global

import net.matees.settings.BooleanSetting
import net.matees.settings.Global
import org.bukkit.Material

class PVPEnabled private constructor() : BooleanSetting(), Global {
    override var setting: Boolean? = true

    override fun onChange() {
    }

    override val name: String
        get() = "PVP Enabled"

    override val description: String
        get() = "Enable or disable PVP"

    override val menuItemMaterial: Material
        get() = Material.IRON_SWORD

    override val menuItemSlot: Int
        get() = 14

    companion object {
        val instance: PVPEnabled = PVPEnabled()
    }
}
