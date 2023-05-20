package me.dieal.playermanager.manager.inventories.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.inventories.OnlinePlayersMenu;
import me.dieal.playermanager.manager.inventories.PlayerInventory;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class OnlineMenuListener implements Listener {

    private PlayerManager manager;

    public OnlineMenuListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMenuClick (InventoryClickEvent e) {

        if (!(e.getInventory().getHolder() instanceof OnlinePlayersMenu)) {
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

                SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();
                UUID target = meta.getOwningPlayer().getUniqueId();
                if (manager.isOnline(target)) {
                    PlayerInventory inventory = new PlayerInventory(target);
                    inventory.openInventory(player);
                } else {
                    player.sendMessage(ChatColor.RED + "The player is not online");
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 5, 3);
                    new OnlinePlayersMenu(player, manager).openMenu();
                }

                break;
        }

    }

}
