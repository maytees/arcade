package net.matees.commands

import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.MenuManager
import net.matees.menus.GlobalSettingsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenGlobalSettingsMenu : SubCommand() {
    override fun getAliases(): List<String>? {
        return null
    }

    override fun getDescription(): String {
        return "Open global settings for Arcade minigames"
    }

    override fun getName(): String {
        return "settings"
    }

    override fun getSubcommandArguments(arg0: Player, arg1: Array<String>): List<String>? {
        return null
    }

    override fun getSyntax(): String {
        return "/arcade settings"
    }

    override fun perform(arg0: CommandSender, arg1: Array<String>) {
        try {
            MenuManager.openMenu(GlobalSettingsMenu::class.java, arg0 as Player)
        } catch (e: MenuManagerException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: MenuManagerNotSetupException) {
            e.printStackTrace()
        }
    }
}
