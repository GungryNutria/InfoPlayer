/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gungrynutria.infoplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author edgar
 */
public class Main extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    Player p;
    Inventory inv, invlands;
    UserInventory us = new UserInventory();
    LandInventory ld = new LandInventory();
    ArrayList<Land> land;
    public static Economy economy = null;
    HashMap<String, ArrayList<Land>> lands;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Plugin Iniciado");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Plugin Finalizado");

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    @EventHandler
    public void touchPlayer(PlayerInteractEntityEvent ev) {
        if (ev.getRightClicked() instanceof Player) {
            Player player = ev.getPlayer();
            p = (Player) ev.getRightClicked();
            us.newUserInventary(p);
            player.openInventory(us.getUserInventary());
        } else {
            Bukkit.broadcastMessage("Ese no es un Usuario");
        }
    }
    @EventHandler
    public void onLandInventoryClick(final InventoryClickEvent e){
        Player player = us.getPlayer();
        if (e.getInventory() == ld.getLandInventory()) {
            e.setCancelled(true);
            final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }
        }
    }
    @EventHandler
    public void onUserInventoryClick(final InventoryClickEvent e) {
        Player player = us.getPlayer();
        if (e.getInventory() == us.getUserInventary()) {
            e.setCancelled(true);
        if (e.getSlot() == 30) {
            land = new ArrayList<>();
            lands = new HashMap<>();
            land.add(new Land(player.getDisplayName(), "Land1", new Location(player.getWorld(), 500.0, 500.0, 500.0)));
            land.add(new Land(player.getDisplayName(), "Land2", new Location(player.getWorld(), 500.0, 500.0, 500.0)));
            land.add(new Land(player.getDisplayName(), "Land3", new Location(player.getWorld(), 500.0, 500.0, 500.0)));
            land.add(new Land(player.getDisplayName(), "Land4", new Location(player.getWorld(), 500.0, 500.0, 500.0)));
            Bukkit.broadcastMessage("Lands Agregadas");
            Bukkit.broadcastMessage(land.get(0).getNombre());
            lands.put(player.getDisplayName(), land);
            Bukkit.broadcastMessage("lands agregadas");
            Bukkit.broadcastMessage(lands.get(player.getDisplayName()).get(0).getNombre());
            ld.newLandInventory(player.getDisplayName(), lands);
            player.closeInventory();
            player.openInventory(ld.getLandInventory());
        }
        }
    }

    @EventHandler
    public void onUserInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == us.getUserInventary()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onLandInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == ld.getLandInventory()) {
            e.setCancelled(true);
        }
    }
    public static Economy getEconomy() {
        return economy;
    }
}
