package me.dieal.playermanager.manager.gui;

import me.dieal.playermanager.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class OnlinePlayersMenu {

    public static Inventory createMenu (Player player, PlayerManager manager)  {

        if (player == null) {
            return null;
        }

        Inventory menu = Bukkit.createInventory(player, 54, ChatColor.RED + "Management Menu");
        menu.setContents(generateContents(menu.getSize(), manager));

        return menu;

    }

    private static ItemStack[] generateContents (int size, PlayerManager manager) {

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

                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwningPlayer(Bukkit.getPlayer(players.get(nItem)));
                head.setItemMeta(meta);

                int itemIndex = (row * 9) + column;
                contents[itemIndex] = head;
                nItem++;

            }
        }

        return contents;

    }

    public static void openMenu (Player player, PlayerManager manager) {

        if (player == null) {
            return;
        }

        player.openInventory(createMenu(player, manager));

    }

}
