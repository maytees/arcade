package net.matees.settings

abstract class AbstractStringSetting : AbstractSetting<String?>(SettingType.StringSetting) {
    override fun setStringValue(value: String?) {
        setting = value
    }
}
