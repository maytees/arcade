package net.matees.arcade

import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.menu.Menu
import net.matees.settings.Setting
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

abstract class Minigame {
    var isCurrentMinigame: Boolean = false

    abstract val name: String

    abstract val minigameType: MinigameType?

    abstract val listeners: List<Listener>

    abstract val subCommands: List<Class<out SubCommand?>?>?

    abstract val commandName: String?

    abstract val commandDescription: String?

    abstract val commandUsage: String?

    abstract val settings: List<Setting<*>>

    abstract val settingsMenu: Class<out Menu?>?

    fun getSetting(name: String): Setting<*>? {
        for (setting in this.settings) {
            if (setting.name == name) {
                return setting
            }
        }

        return null
    }

    abstract val minigameMenuItem: ItemStack

    abstract fun doStartMinigame()

    fun startMinigame() {
        this.isCurrentMinigame = true
        this.doStartMinigame()
    }

    fun stopMinigame() {
        this.isCurrentMinigame = false
        this.onStopMinigame()
    }

    abstract fun onStopMinigame()
}
