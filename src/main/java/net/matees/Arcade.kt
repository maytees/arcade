package net.matees

import me.kodysimpson.simpapi.command.CommandManager
import me.kodysimpson.simpapi.menu.MenuManager
import net.matees.arcade.Minigame
import net.matees.arcade.itemrush.ItemRush
import net.matees.arcade.lavarise.LavaRise
import net.matees.arcade.mobrush.MobRush
import net.matees.commands.OpenArcadeMenu
import net.matees.commands.OpenGlobalSettingsMenu
import net.matees.settings.global.GlobalSettings
import org.bukkit.plugin.java.JavaPlugin

class Arcade : JavaPlugin() {
    var currentGame: Minigame? = null

    var minigames: List<Minigame>? = null
        private set

    override fun onEnable() {
        // Plugin startup logic
        plugin = this
        MenuManager.setup(server, this)

        try {
            initializeCommands()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        setupArcade()
    }

    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    private fun initializeCommands() {
        CommandManager.createCoreCommand(
            this, "arcade", "Arcade plugin - Survival minigames", "/arcade open|settings",
            null, OpenArcadeMenu::class.java, OpenGlobalSettingsMenu::class.java
        )
    }

    private fun setupArcade() {
        minigames = listOf<Minigame>(
            ItemRush.instance,
            MobRush.instance,  // Manhunt.getInstance(), WIP
            LavaRise.instance
        )

        for (listener in GlobalSettings.Companion.instance.listeners) {
            server.pluginManager.registerEvents(listener, this)
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        var plugin: Arcade? = null
            private set
    }
}
