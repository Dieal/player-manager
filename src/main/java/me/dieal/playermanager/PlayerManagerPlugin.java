package me.dieal.playermanager;

import me.dieal.playermanager.commands.ManagerCommand;
import me.dieal.playermanager.listeners.UpdaterListener;
import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.listeners.MainMenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerManagerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // Object that manages online players (add / remove / update)
        PlayerManager manager = new PlayerManager();

        // Commands
        getCommand("manager").setExecutor(new ManagerCommand(manager));

        // Listeners
        getServer().getPluginManager().registerEvents(new UpdaterListener(manager), this);
        getServer().getPluginManager().registerEvents(new MainMenuListener(manager), this);

    }

}
