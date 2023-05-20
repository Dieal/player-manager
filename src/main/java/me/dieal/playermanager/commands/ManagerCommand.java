package me.dieal.playermanager.commands;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.inventories.MainMenu;
import org.bukkit.ChatColor;
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

        Player player = (Player) commandSender;
        if (!player.hasPermission("manager.menu")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to open the manager menu");
            return true;
        }

        if (strings.length > 0) {
            player.sendMessage(ChatColor.RED + "Correct usage: /manager");
            return true;
        }

        MainMenu inventory = new MainMenu (player, manager);
        inventory.openMenu();
        return true;
    }

}
