package net.matees.arcade.lavarise.settings

import net.matees.settings.IntegerSetting
import org.bukkit.Material

class TimeToRise private constructor() : IntegerSetting() {
    private var setting = 5 // Seconds

    override fun getName(): String {
        return "Time To Rise"
    }

    override fun getDescription(): String {
        return "Set how often lava rises"
    }

    override fun getSetting(): Int {
        return this.setting
    }

    override fun setSetting(setting: Int) {
        this.setting = setting
    }

    override fun getMenuItemMaterial(): Material {
        return Material.RABBIT_FOOT
    }

    override fun getMenuItemSlot(): Int {
        return 15
    }

    companion object {
        val instance: TimeToRise = TimeToRise()
    }
}
