package net.matees.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import net.matees.menus.GlobalSettingsMenu;

public class OpenGlobalSettingsMenu extends SubCommand {

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Open global settings for Arcade minigames";
    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public List<String> getSubcommandArguments(Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public String getSyntax() {
        return "/arcade settings";
    }

    @Override
    public void perform(CommandSender arg0, String[] arg1) {
        try {
            MenuManager.openMenu(GlobalSettingsMenu.class, (Player) arg0);
        } catch (MenuManagerException | MenuManagerNotSetupException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
