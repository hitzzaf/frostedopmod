package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class F_BlockListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public F_BlockListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        
        if (ConfigEntry.MainConfig().getBoolean("disabled.cmdblock")) {
            if (event.getBlock().getType() == Material.COMMAND) {
                event.getPlayer().sendMessage(ChatColor.GRAY + "Command Blocks placement is currently disabled!");
                event.setCancelled(true);
            }
        }
        
        if (ConfigEntry.MainConfig().getBoolean("disabled.lava-place")) {
            if (event.getBlock().getType() == Material.LAVA && event.getBlock().getType() == Material.LAVA_BUCKET) {
                event.getPlayer().sendMessage(ChatColor.GRAY + "Lava placement is currently disabled!");
                event.setCancelled(true);
            }
        }
        
        if (ConfigEntry.MainConfig().getBoolean("disabled.water-place")) {
            if (event.getBlock().getType() == Material.WATER && event.getBlock().getType() == Material.WATER_BUCKET) {
                event.getPlayer().sendMessage(ChatColor.GRAY + "Water placement is currently disabled!");
                event.setCancelled(true);
            }
        }
    }
}
