package net.matees.settings

abstract class AbstractIntegerSetting : AbstractSetting<Int?>(SettingType.IntegerSetting) {
    open val min = 1

    override fun setIntValue(value: Int) {
        if(value < min) return
        setting = value
    }
}
