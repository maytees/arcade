package net.matees.commands

import me.kodysimpson.simpapi.command.SubCommand
import me.kodysimpson.simpapi.exceptions.MenuManagerException
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException
import me.kodysimpson.simpapi.menu.MenuManager
import net.matees.menus.MinigameMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenArcadeMenu : SubCommand() {
    override fun getAliases(): List<String>? {
        return null
    }

    override fun getDescription(): String {
        return "Open the minigames menu, which allows you to edit settings, and start minigames"
    }

    override fun getName(): String {
        return "open"
    }

    override fun getSubcommandArguments(arg0: Player, arg1: Array<String>): List<String>? {
        return null
    }

    override fun getSyntax(): String {
        return "/arcade open"
    }

    override fun perform(commandSender: CommandSender, arg1: Array<String>) {
        try {
            MenuManager.openMenu(MinigameMenu::class.java, commandSender as Player)
        } catch (e: MenuManagerException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: MenuManagerNotSetupException) {
            e.printStackTrace()
        }
    }
}
