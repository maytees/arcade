package net.matees.settings

import org.bukkit.entity.Player

abstract class AbstractPlayerBooleanSetting :
    AbstractSetting<HashMap<Player, Boolean>?>(SettingType.PlayerBooleanSetting) {
    override fun setPlayerBooleanSetting(value: HashMap<Player, Boolean>?) {
        setting = value
    }
}
