package me.dieal.playermanager.commands;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.gui.MainMenu;
import me.dieal.playermanager.manager.gui.OnlinePlayersMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ManagerCommand implements CommandExecutor {

    public PlayerManager manager;

    public ManagerCommand (PlayerManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) {
            return true;
        }

        if (strings.length != 0) {
            return true;
        }

        Player player = (Player) commandSender;
        MainMenu.openMenu(player, manager);

        return true;
    }

}
