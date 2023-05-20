package me.dieal.playermanager.manager.inventories;

import me.dieal.playermanager.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class BannedPlayersMenu implements InventoryHolder {

    private final Inventory inventory;
    private final Player player;
    private final PlayerManager manager;

    public BannedPlayersMenu (Player player, PlayerManager manager) {
        this.player = player;
        this.manager = manager;
        this.inventory = createMenu();
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    private Inventory createMenu ()  {
        Inventory menu = Bukkit.createInventory(this, 54, ChatColor.RED + "Banned Players");
        manager.updateBannedPlayers();
        menu.setContents(generateContents(menu.getSize()));

        return menu;
    }

    private ItemStack[] generateContents (int size) {

        ItemStack[] contents = new ItemStack[size];
        ArrayList<UUID> bannedPlayers = manager.getBannedPlayers();

        if (bannedPlayers.isEmpty()) {
            return contents;
        }

        int nColumns = 9;
        int nRows = size / nColumns;

        int nItem = 0;
        for (int row = 0; (row < nRows) && (nItem < bannedPlayers.size()); row++) {
            player.sendMessage("Entrato nel ciclo");

            if (row == 0 || row == 8) {
                continue;
            }

            for (int column = 0; (column < nColumns) && (nItem < bannedPlayers.size()); column++) {

                if (column == 0 || column == 8) {
                    continue;
                }

                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(bannedPlayers.get(nItem));
                if (!p.hasPlayedBefore()) {
                    continue;
                }

                ArrayList<String> lore = new ArrayList<>();
                Date expirationDate = PlayerManager.getBanExpirationDate(p);
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();

                meta.setOwningPlayer(p);
                meta.setDisplayName(ChatColor.WHITE + p.getName());

                if (expirationDate != null) {
                    lore.add(ChatColor.RED + "Expiration Date: " + ChatColor.WHITE + expirationDate.toString());
                } else {
                    lore.add(ChatColor.RED + "Expiration Date: " + ChatColor.WHITE + "//");
                }

                lore.add("");
                lore.add(ChatColor.YELLOW + "" + ChatColor.YELLOW + "Click here to unban the player");
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
        player.openInventory(inventory);
    }

}

