/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gungrynutria.infoplayer;

import org.bukkit.Location;

public class Land {
    
    String owner;
    String nombre;
    Location tp;
    
    public Land(String owner, String nombre, Location tp){
        this.owner = owner;
        this.nombre = nombre;
        this.tp = tp;
        
    }
    
    public String getOwner(){
        return this.owner;
    }
    public String getNombre(){
        return this.nombre;
    }
    public Location getLocation(){
        return this.tp;
    }
}
