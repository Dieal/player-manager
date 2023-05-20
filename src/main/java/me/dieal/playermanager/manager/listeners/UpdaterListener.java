package me.dieal.playermanager.manager.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UpdaterListener implements Listener {

    private PlayerManager manager;

    public UpdaterListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {
        manager.addOnlinePlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave (PlayerQuitEvent e) {
        manager.removeOnlinePlayer(e.getPlayer());
    }

}
