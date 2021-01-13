/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gungrynutria.infoplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author edgar
 */
public class LandInventory {
    Inventory inv;
    ArrayList<Land> land;
    public void newLandInventory(String username,HashMap<String, ArrayList<Land>> lands){
        inv = Bukkit.createInventory(null, 54,  username + " Lands");
        land = lands.get(username);
            for (int j = 0; j < land.size(); j++) {
             inv.setItem(j,createLandBlock(Material.GRASS_BLOCK, land.get(j).getNombre()));   
            }
        
    }
    protected ItemStack createLandBlock(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
    public Inventory getLandInventory(){
        return inv;
    }
    
}
