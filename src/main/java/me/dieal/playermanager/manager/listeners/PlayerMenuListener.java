package me.dieal.playermanager.manager.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.gui.OnlinePlayersMenu;
import me.dieal.playermanager.manager.gui.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class PlayerMenuListener implements Listener {

    private PlayerManager manager;

    public PlayerMenuListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick (InventoryClickEvent e) {

        e.getWhoClicked().sendMessage("click");
        Inventory inventory = e.getInventory();
        if (!(e.getInventory().getHolder() instanceof PlayerInventory)) {
            return;
        }
        e.getWhoClicked().sendMessage("that's my inventory!");

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
                manager.permaBanPlayer(target, "Sei stupido", sender);
                break;
            case IRON_DOOR:
                player.closeInventory();
                break;
            case ZOMBIE_HEAD:
                player.closeInventory();
                PlayerManager.killPlayer(target);
                break;
            case ELYTRA:
                player.closeInventory();
                // fly
                break;
            case ENCHANTED_BOOK:
                player.closeInventory();
                PlayerManager.teleportToTarget(sender, target);
                break;
            case COOKED_BEEF:
                player.closeInventory();
                PlayerManager.healPlayer(target);
                break;
        }

    }

}
