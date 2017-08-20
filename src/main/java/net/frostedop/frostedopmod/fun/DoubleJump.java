package net.frostedop.frostedopmod.fun;

import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump implements Listener 
{
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public DoubleJump() 
    {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) 
    {
        if (ConfigEntry.MainConfig().getBoolean("fun.djump"))
        {
            Player player = event.getPlayer();
            if (player.getGameMode() == GameMode.CREATIVE) return;
        
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) 
    {
        if (ConfigEntry.MainConfig().getBoolean("fun.djump"))
        {
            Player player = event.getPlayer();
            if ((player.getGameMode() != GameMode.CREATIVE) && (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!player.isFlying())) {
                player.setAllowFlight(true);
            }
        }
    }
}