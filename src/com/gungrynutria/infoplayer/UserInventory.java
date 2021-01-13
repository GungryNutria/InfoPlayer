/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gungrynutria.infoplayer;

import static com.gungrynutria.infoplayer.Main.economy;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author edgar
 */
public class UserInventory {
    Player player;
    Inventory inv;
    World w;

    public void newUserInventary(Player p) {
        this.player = p;
        inv = Bukkit.createInventory(null, 54, p.getDisplayName() + " Profile");
        if (p.getEquipment().getHelmet() == null) {
            inv.setItem(10, createItem(Material.GLASS, "No Helmet"));
        } else {
            inv.setItem(10, p.getEquipment().getHelmet());
        }
        if (p.getEquipment().getChestplate() == null) {
            inv.setItem(19, createItem(Material.GLASS, "No ChestPlate"));
        } else {
            inv.setItem(19, p.getEquipment().getChestplate());
        }
        if (p.getEquipment().getLeggings() == null) {
            inv.setItem(28, createItem(Material.GLASS, "No Leggings"));
        } else {
            inv.setItem(28, p.getEquipment().getLeggings());
        }
        if (p.getEquipment().getBoots() == null) {
            inv.setItem(37, createItem(Material.GLASS, "No Boots"));
        } else {
            inv.setItem(37, p.getEquipment().getBoots());
        }
        inv.setItem(12, ownerHead(p));
        inv.setItem(21, createItem(Material.REDSTONE, String.valueOf(p.getHealthScale()), "Vida"));
        inv.setItem(22, createItem(Material.GOLD_INGOT, economy.format(economy.getBalance(p.getName())), "Creditos"));
        inv.setItem(30, createItem(Material.GRASS_BLOCK, "Lands"));
        inv.setItem(31, createItem(Material.BIRCH_SIGN, "Tienda", "N Objetos en venta"));
        switch (p.getWorld().getEnvironment()) {
            case NORMAL:
                inv.setItem(13, createItem(Material.ZOMBIE_HEAD, "Tierra"));
                break;
            case NETHER:
                inv.setItem(13, createItem(Material.WITHER_SKELETON_SKULL, "Nether"));
                break;
            case THE_END:
                inv.setItem(13, createItem(Material.DRAGON_HEAD, "End"));
                break;
            default:
                break;
        }
    }

    protected ItemStack createItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    protected ItemStack ownerHead(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        meta.setDisplayName(p.getDisplayName());
        item.setItemMeta(meta);
        return item;
    }

    public Inventory getUserInventary() {
        return inv;
    }
    public Player getPlayer(){
        return player;
    }
}
