package net.matees.arcade.itemrush.settings

import net.matees.settings.IntegerSetting
import org.bukkit.Material

class MaxItemCount private constructor() : IntegerSetting() {
    private var maxItemCount = 16

    override val name: String
        get() = "Max Item Count"

    override val description: String
        get() = "Max amount of items dropped (if random is enabled, this is the range)"

    override var setting: Int?
        get() = maxItemCount
        set(setting) {
            if (setting != null) {
                this.maxItemCount = setting
            }
        }

    override val menuItemMaterial: Material
        get() = Material.STICK

    override val menuItemSlot: Int
        get() = 12

    companion object {
        val instance: MaxItemCount = MaxItemCount()
    }
}
