package net.frostedop.frostedopmod.listeners;

import java.util.Arrays;
import net.frostedop.frostedopmod.F_Util;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_MOTD_L1;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_MOTD_L2;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_NAME;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class F_MainListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public F_MainListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        
        if(ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".frozen")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't move while frozen!");
            event.setCancelled(true);
            event.getPlayer().teleport(event.getPlayer());
        }
    }
    
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().split(" ")[0].contains(":")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You cannot send plugin specific commands.");
            event.setCancelled(true);
        }
        
        if (ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".cmdsblcked")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "Your commands are currently blocked!");
            event.setCancelled(true);
        }
        
        if (ConfigEntry.AdminConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".cmdspy")) {
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(event.getPlayer() + ": " + event.getMessage().toLowerCase());
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event) {
        
        if(Arrays.asList(Bukkit.getOnlinePlayers()).size() >= Bukkit.getMaxPlayers()) {
            event.setMotd(F_Util.color(ConfigEntry.MainConfig().getString("server.motd-full-server").replace("%servername%", ConfigEntry.MainConfig().getString("server.name"))));
        }
        
        String lineone = ConfigEntry.MainConfig().getString(S_MOTD_L1);
        String linetwo = ConfigEntry.MainConfig().getString(S_MOTD_L2).replace("%servername%", ConfigEntry.MainConfig().getString(S_NAME));
        String Motd = lineone + " \n" + linetwo;
        Motd = F_Util.color(Motd);
        event.setMotd(Motd.trim());
    }
}