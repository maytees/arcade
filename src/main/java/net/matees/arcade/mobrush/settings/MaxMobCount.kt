package net.matees.arcade.mobrush.settings

import net.matees.settings.IntegerSetting
import org.bukkit.Material

class MaxMobCount private constructor() : IntegerSetting() {
    private var maxMobCount = 1

    override val name: String
        get() = "Max Mob Count"

    override val description: String
        get() = "Max amount of mobs which spawn (if random is enabled, this is the max range)"

    override var setting: Int?
        get() = maxMobCount
        set(setting) {
            if (setting != null) {
                this.maxMobCount = setting
            }
        }

    override val menuItemMaterial: Material
        get() = Material.ZOMBIE_HEAD

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: MaxMobCount = MaxMobCount()
    }
}
