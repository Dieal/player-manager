package me.dieal.playermangaer;

import me.dieal.playermangaer.commands.ManagerCommand;
import me.dieal.playermangaer.manager.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerManagerPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        PlayerManager manager = new PlayerManager();

        getCommand("manager").setExecutor(new ManagerCommand());
    }

}
