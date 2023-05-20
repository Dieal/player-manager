package me.dieal.playermanager.manager.inventories;

import me.dieal.playermanager.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class MainMenu implements InventoryHolder {

    private Inventory inventory;
    private Player player;

    public MainMenu (Player player, PlayerManager manager) {
        this.player = player;
        this.inventory = createMenu();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private Inventory createMenu ()  {

        Inventory menu = Bukkit.createInventory(this, 27, ChatColor.RED + "Management Menu");

        ItemStack onlinePlayers = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta onlineMeta = (SkullMeta) onlinePlayers.getItemMeta();
        onlineMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("e90d38f6-d4e1-447b-8e7a-4ea11a14ef9a")));
        onlineMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Online Players");
        onlinePlayers.setItemMeta(onlineMeta);

        ItemStack bannedPlayers = new ItemStack(Material.BARRIER);
        ItemMeta bannedMeta = bannedPlayers.getItemMeta();
        bannedMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Banned Players");
        bannedPlayers.setItemMeta(bannedMeta);

        menu.setItem(11, onlinePlayers);
        menu.setItem(15, bannedPlayers);

        return menu;

    }

    public void openMenu () {
        player.openInventory(inventory);
    }

}
