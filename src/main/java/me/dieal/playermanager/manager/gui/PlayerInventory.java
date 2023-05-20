package me.dieal.playermanager.manager.gui;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerInventory implements InventoryHolder {

    private UUID target;
    private Inventory inventory;

    // Constructors
    public PlayerInventory (UUID target) {
        this.target = target;
        this.inventory = createInventory(target);
    }

    public PlayerInventory (Player target) {
        this.target = target.getUniqueId();
        this.inventory = createInventory(target.getUniqueId());
    }

    // Getters
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public UUID getTarget() {
        return target;
    }

    public void openInventory (Player player) {

        if (player == null) {
            return;
        }

        player.openInventory(inventory);

    }

    // Menu creation
    private Inventory createInventory (UUID target) {

        Player player = Bukkit.getPlayer(target);
        Inventory inv = Bukkit.createInventory(this, 45, ChatColor.RED + "Player Manager: " + ChatColor.WHITE + player.getName());

        ItemStack head = getPlayerHead(player);
        ItemStack ban = getBanItem();
        ItemStack mute = getMuteItem();
        ItemStack teleport = getTeleportItem();
        ItemStack fly = getFlyItem();
        ItemStack heal = getHealItem();
        ItemStack kill = getKillItem();

        inv.setItem(13, head);
        inv.setItem(21, ban);
        inv.setItem(22, mute);
        inv.setItem(23, kill);
        inv.setItem(30, fly);
        inv.setItem(31, teleport);
        inv.setItem(32, heal);

        return inv;

    }

    private ItemStack getPlayerHead (Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(ChatColor.BLUE + "Manage " + player.getName());
        head.setItemMeta(meta);

        return head;
    }

    private ItemStack getBanItem () {
        ItemStack ban = new ItemStack (Material.BARRIER);
        ItemMeta banMeta = ban.getItemMeta();
        banMeta.setDisplayName(ChatColor.RED + "BAN");
        ban.setItemMeta(banMeta);
        return ban;
    }

    private ItemStack getMuteItem () {
        ItemStack mute = new ItemStack (Material.IRON_DOOR);
        ItemMeta muteMeta = mute.getItemMeta();
        muteMeta.setDisplayName(ChatColor.GREEN + "MUTE");
        mute.setItemMeta(muteMeta);
        return mute;
    }

    private ItemStack getHealItem () {
        ItemStack heal = new ItemStack (Material.COOKED_BEEF);
        ItemMeta healMeta = heal.getItemMeta();
        healMeta.setDisplayName(ChatColor.YELLOW + "HEAL");
        heal.setItemMeta(healMeta);
        return heal;
    }

    private ItemStack getKillItem () {
        ItemStack kill = new ItemStack (Material.ZOMBIE_HEAD);
        ItemMeta killMeta = kill.getItemMeta();
        killMeta.setDisplayName(ChatColor.RED + "KILL");
        kill.setItemMeta(killMeta);
        return kill;
    }

    private ItemStack getTeleportItem () {
        ItemStack teleport = new ItemStack (Material.ENCHANTED_BOOK);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(ChatColor.DARK_GREEN + "TELEPORT");
        teleport.setItemMeta(teleportMeta);
        return teleport;
    }

    private ItemStack getFlyItem () {
        ItemStack fly = new ItemStack (Material.ELYTRA);
        ItemMeta flyMeta = fly.getItemMeta();
        flyMeta.setDisplayName(ChatColor.AQUA + "TOGGLE FLY");
        fly.setItemMeta(flyMeta);
        return fly;
    }

}
