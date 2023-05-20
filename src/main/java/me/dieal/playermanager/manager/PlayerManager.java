package me.dieal.playermanager.manager;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
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

    public boolean isOnline (Player player) {

        boolean result = false;

        if (player == null) {
            return result;
        }

        if (Bukkit.getOnlinePlayers().contains(player)) {
            result = true;
        }

        return result;

    }

    public boolean isOnline (UUID player) {

        boolean result = false;

        if (player == null) {
            return result;
        }

        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(player))) {
            result = true;
        }

        return result;

    }

    // Player management
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

    // Ban player
    public void permaBanPlayer (@NotNull Player target, @NotNull String reason, @Nullable Player sender) {

        if (target == null || reason == null || sender == null) {
            return;
        }

        String targetName = target.getName();
        String senderName = sender.getName();

        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(targetName, reason, null, senderName);
        target.kickPlayer("You've been banned.\nReason: \"" + reason + "\"");

    }

    public void banPlayer (@NotNull Player target, @NotNull String reason, @Nullable Date expirationDate, @Nullable Player sender) {

        if (target == null || reason == null || expirationDate == null || sender == null) {
            return;
        }

        String targetName = target.getName();
        String senderName = sender.getName();

        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(targetName, reason, expirationDate, senderName);
        target.kickPlayer("You've been banned.\nReason: \"" + reason + "\"");

    }

    // Pardon player
    public void pardonPlayer (Player player) {
        Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(player.getName());
    }

    // Kill player
    public static void killPlayer (Player player) {

        if (player == null) {
            return;
        }

        player.setHealth(0.0);

    }

    // Heal player
    public static void healPlayer (Player player) {

        if (player == null) {
            return;
        }

        player.setHealth(20);

    }

    // Teleport to target
    public static void teleportToTarget (Player sender, Player target) {

        if (sender == null || target == null) {
            return;
        }

        sender.teleport(target);

    }

    // Update player list
    public void updateOnlinePlayers () {
        onlinePlayers = generateOnlinePlayers();
    }

}
