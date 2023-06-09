package me.dieal.playermanager.manager.inventories;

import me.dieal.playermanager.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class OnlinePlayersMenu implements InventoryHolder {

    private Inventory inventory;
    private Player player;
    private PlayerManager manager;

    public OnlinePlayersMenu (Player player, PlayerManager manager) {
        this.player = player;
        this.manager = manager;
        this.inventory = createMenu();
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    private Inventory createMenu ()  {

        if (player == null) {
            return null;
        }

        Inventory menu = Bukkit.createInventory(this, 54, ChatColor.RED + "Online Players");
        menu.setContents(generateContents(menu.getSize()));

        return menu;

    }

    private ItemStack[] generateContents (int size) {

        ItemStack[] contents = new ItemStack[size];
        ArrayList<UUID> players = manager.getOnlinePlayers();

        if (players.size() < 0) {
            return contents;
        }

        int nColumns = 9;
        int nRows = size / nColumns;

        int nItem = 0;
        for (int row = 0; (row < nRows) && (nItem < players.size()); row++) {

            if (row == 0 || row == 8) {
                continue;
            }

            for (int column = 0; (column < nColumns) && (nItem < players.size()); column++) {

                if (column == 0 || column == 8) {
                    continue;
                }

                Player p = Bukkit.getPlayer(players.get(nItem));
                ArrayList<String> lore = new ArrayList<>();
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();

                meta.setOwningPlayer(p);
                meta.setDisplayName(ChatColor.WHITE + p.getName());
                lore.add(ChatColor.BLUE + "Coordinates: " + ChatColor.WHITE + (int) p.getLocation().getX() + ", " + (int) p.getLocation().getY() + ", " + (int) p.getLocation().getZ());
                lore.add("");
                lore.add(ChatColor.YELLOW + "" + ChatColor.YELLOW + "Click here to manage the player");
                meta.setLore(lore);
                head.setItemMeta(meta);

                int itemIndex = (row * 9) + column;
                contents[itemIndex] = head;
                nItem++;

            }
        }

        return contents;

    }

    public void openMenu () {

        if (player == null) {
            return;
        }

        player.openInventory(inventory);

    }

}
