package net.matees.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import net.matees.menus.MinigameMenu;

public class OpenArcadeMenu extends SubCommand {

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Open the minigames menu, which allows you to edit settings, and start minigames";
    }

    @Override
    public String getName() {
        return "open";
    }

    @Override
    public List<String> getSubcommandArguments(Player arg0, String[] arg1) {
        return null;
    }

    @Override
    public String getSyntax() {
        return "/arcade open";
    }

    @Override
    public void perform(CommandSender commandSender, String[] arg1) {
        try {
            MenuManager.openMenu(MinigameMenu.class, (Player) commandSender);
        } catch (MenuManagerException | MenuManagerNotSetupException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
}
