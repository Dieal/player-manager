package me.dieal.playermanager.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerManager {

    private ArrayList<UUID> onlinePlayers;

    // Constructor
    public PlayerManager () {
        updateOnlinePlayers();
    }

    private ArrayList<UUID> generateOnlinePlayers () {

        ArrayList<UUID> users = new ArrayList<>() ;

        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            users.add(p.getUniqueId());
        }

        return users;

    }

    // Getters
    public ArrayList<UUID> getOnlinePlayers () {
        return new ArrayList<UUID>(onlinePlayers);
    }

    // Add player
    public void addPlayer (Player p) {

        if (p == null) {
            return;
        }

        if (!p.isOnline()) {
            return;
        }

        onlinePlayers.add(p.getUniqueId());

    }

    // Remove player
    public void removePlayer (Player p) {

        if (p == null) {
            return;
        }

        if (onlinePlayers.size() == 0 || !onlinePlayers.contains(p.getUniqueId())) {
            return;
        }

        onlinePlayers.remove(p.getUniqueId());

    }

    // Updates player list
    public void updateOnlinePlayers () {
        onlinePlayers = generateOnlinePlayers();
    }

}
