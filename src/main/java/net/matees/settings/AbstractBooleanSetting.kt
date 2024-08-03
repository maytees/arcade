package net.matees.settings

abstract class AbstractBooleanSetting : AbstractSetting<Boolean?>(SettingType.BooleanSetting) {
    override fun setBooleanValue(value: Boolean) {
        setting = value
    }
}
