package me.dieal.playermanager.manager.listeners;

import me.dieal.playermanager.manager.PlayerManager;
import me.dieal.playermanager.manager.inventories.MainMenu;
import me.dieal.playermanager.manager.inventories.OnlinePlayersMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainMenuListener implements Listener {

    private PlayerManager manager;

    public MainMenuListener (PlayerManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onClickEvent (InventoryClickEvent e) {

        if (!(e.getInventory().getHolder() instanceof MainMenu)) {
            return;
        }

        if (e.getCurrentItem() == null) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        switch (e.getCurrentItem().getType()) {
            case PLAYER_HEAD:
                OnlinePlayersMenu inventory = new OnlinePlayersMenu(player, manager);
                player.closeInventory();
                inventory.openMenu();
                break;
            case BARRIER:
                player.closeInventory();
                // banned players menu
                break;
        }

    }

}
