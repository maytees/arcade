package net.matees.settings.global

import net.matees.settings.BooleanSetting
import net.matees.settings.Global
import org.bukkit.Material

class EnableNetherPortal private constructor() : BooleanSetting(), Global {
    override var setting: Boolean? = false

    override val name: String
        get() = "Enable Nether Portal"

    override val description: String
        get() = "Enable or disable nether portals"

    override val menuItemMaterial: Material?
        get() = Material.OBSIDIAN

    override val menuItemSlot: Int
        get() = 13

    override fun onChange() {
    }

    companion object {
        val instance: EnableNetherPortal = EnableNetherPortal()
    }
}
