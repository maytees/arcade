package net.matees.settings.global

import net.matees.settings.AbstractBooleanSetting
import net.matees.settings.IGlobal
import org.bukkit.Material

class EnableNetherPortal private constructor() : AbstractBooleanSetting(), IGlobal {
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
