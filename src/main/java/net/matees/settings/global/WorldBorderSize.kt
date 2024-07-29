package net.matees.settings.global

import net.matees.settings.Global
import net.matees.settings.IntegerSetting
import org.bukkit.Bukkit
import org.bukkit.Material

class WorldBorderSize private constructor() : IntegerSetting(), Global {
    override var setting: Int? = 500

    override val name: String
        get() = "World Border Size"

    override val description: String
        get() = "Set world border size"

    override val menuItemMaterial: Material?
        get() = Material.IRON_BARS

    override val menuItemSlot: Int
        get() = 11

    override fun onChange() {
        Bukkit.getWorld("world")!!.worldBorder.size = setting!!.toDouble()
    }

    companion object {
        val instance: WorldBorderSize = WorldBorderSize()
    }
}
