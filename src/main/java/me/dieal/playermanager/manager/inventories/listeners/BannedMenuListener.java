package me.dieal.playermanager.manager.inventories.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.inventories.BannedPlayersMenu;
import me.dieal.playermanager.manager.inventories.OnlinePlayersMenu;
import me.dieal.playermanager.manager.inventories.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class BannedMenuListener implements Listener {

    private PlayerManager manager;

    public BannedMenuListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick (InventoryClickEvent e) {

        if (!(e.getInventory().getHolder() instanceof BannedPlayersMenu)) {
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

        switch (e.getCurrentItem().getType()) {
            case PLAYER_HEAD:
                player.closeInventory();

                if (!player.hasPermission("manager.pardon")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to pardon a banned player");
                    return;
                }

                SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer target = meta.getOwningPlayer();
                manager.pardonPlayer(target);
                player.sendMessage(ChatColor.YELLOW + "The player " + target.getName() + " has been unbanned");
                player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 3);
                new BannedPlayersMenu(player, manager).openMenu();

                break;
        }

    }
}
