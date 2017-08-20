package net.frostedop.frostedopmod.banning;

import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class BanListener implements Listener
{
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public BanListener() 
    {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        
        if (BanningSystem.isBanned(player) && !ConfigEntry.MainConfig().contains("famous_players") && !Rank.isAdmin(player)) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.GOLD + "You are currently banned from this server! "
                    + "\nAppeal at: " + ConfigEntry.MainConfig().getString("server.gtfo-url")
            + ChatColor.RED + "\nReason: " + ChatColor.GOLD + BanningSystem.getBanReason(player) + ChatColor.RED 
                    + "\nBanned by: " + ChatColor.GOLD + BanningSystem.getBanner(player));
        }
        
        if (PermbanSystem.PPB_IP_BANS.contains(player.getAddress().getHostString())) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your IP address is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
        
        if (PermbanSystem.PPB_PLAYERS.contains(player.getName())) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your username is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
        
        if (PermbanSystem.PPB_UUIDS.contains(player.getUniqueId().toString())) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your UUID is permanently banned from this server"
                    + "\rRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
        
        if (ConfigEntry.PermbanConfig().getStringList("uuids").contains(player.getUniqueId().toString())) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your UUID is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
        
        if (ConfigEntry.PermbanConfig().getStringList("ips").contains(player.getAddress().getHostString())) 
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your IP is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
    }
}
