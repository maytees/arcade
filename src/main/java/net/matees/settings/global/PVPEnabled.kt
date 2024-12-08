package net.matees.settings.global

import net.matees.settings.AbstractBooleanSetting
import net.matees.settings.IGlobal
import org.bukkit.Bukkit
import org.bukkit.Material

class PVPEnabled private constructor() : AbstractBooleanSetting(), IGlobal {
    override var setting: Boolean? = true

    override fun onChange() {
        val world = Bukkit.getWorld("world")
        if (world != null) {
            world.pvp = this.setting!!;
        }
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
