package me.dieal.playermanager.manager.inventories.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.inventories.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PlayerMenuListener implements Listener {

    private final PlayerManager manager;

    public PlayerMenuListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick (InventoryClickEvent e) {

        Inventory inventory = e.getInventory();
        if (!(e.getInventory().getHolder() instanceof PlayerInventory)) {
            return;
        }

        if (e.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (!(e.getClick() == ClickType.LEFT)) {
            return;
        }

        PlayerInventory holder = (PlayerInventory) inventory.getHolder();
        Player target = Bukkit.getPlayer(holder.getTarget());
        Player sender = (Player) e.getWhoClicked();
        switch (e.getCurrentItem().getType()) {
            case BARRIER:
                player.closeInventory();

                if (!player.hasPermission("manager.ban")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to manage a player");
                    return;
                }

                manager.permaBanPlayer(target, "Sei stupido", sender);
                break;
            case IRON_DOOR:
                player.closeInventory();

                if (!player.hasPermission("manager.ban")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to mute a player");
                    return;
                }

                break;
            case ZOMBIE_HEAD:
                player.closeInventory();

                if (!player.hasPermission("manager.kill")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to kill a player");
                    return;
                }

                PlayerManager.killPlayer(target);
                break;
            case ELYTRA:
                player.closeInventory();
                // fly
                break;
            case ENCHANTED_BOOK:
                player.closeInventory();

                if (!player.hasPermission("manager.teleport")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to teleport to a player");
                    return;
                }

                PlayerManager.teleportToTarget(sender, target);
                break;
            case COOKED_BEEF:

                if (!player.hasPermission("manager.heal")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to heal a player");
                    return;
                }

                player.closeInventory();
                PlayerManager.healPlayer(target);
                break;
        }

    }

}
