package net.matees.settings.global

import net.matees.settings.*
import org.bukkit.Bukkit
import org.bukkit.Material

class EnableWorldBorder private constructor() : AbstractBooleanSetting(), IGlobal {
    override var setting: Boolean? = false

    override val name: String
        get() = "Enable World Border"

    override val description: String
        get() = "Determines whether there is a world border"

    override val menuItemMaterial: Material?
        get() = Material.CYAN_GLAZED_TERRACOTTA

    override val menuItemSlot: Int
        get() = 10

    override fun onChange() {
        val size = GlobalSettings.Companion.instance.getSetting("World Border Size")?.setting as Int

        Bukkit.getWorld("world")!!.worldBorder.size = if (this.setting == true
        ) size.toDouble() else 30000000.0
    }

    companion object {
        val instance: EnableWorldBorder = EnableWorldBorder()
    }
}
