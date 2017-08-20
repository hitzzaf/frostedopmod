package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.F_Util;
import net.frostedop.frostedopmod.FrostedOPMod;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_CHATCOLOR;
import net.frostedop.frostedopmod.ranks.RankDisplay;
import net.frostedop.frostedopmod.ranks.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class F_RankListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public F_RankListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();
            
            final Player player = event.getPlayer();
            
            if (Ranks.isSuperAdmin(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.SUPER_ADMIN, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.SUPER_ADMIN, player);
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.SUPER_ADMIN) + "Admin " + P_CHATCOLOR + player.getName()));
            }
            
            if (Ranks.isMasterBuilder(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.MASTER_BUILDER, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.MASTER_BUILDER, player);
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.MASTER_BUILDER) + "Builder " + P_CHATCOLOR + player.getName()));
            }
            
            else if (Ranks.isTelnetAdmin(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.TELNET_ADMIN, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.TELNET_ADMIN, player); 
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.TELNET_ADMIN) + "Telnet " + P_CHATCOLOR + player.getName()));
            }
            
            else if (Ranks.isSeniorAdmin(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.SENIOR_ADMIN, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.SENIOR_ADMIN, player);
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.SENIOR_ADMIN) + "Senior " + P_CHATCOLOR + player.getName()));
            }
            
            else if (Ranks.isExecutive(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.EXECUTIVE, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.EXECUTIVE, player);
                player.setPlayerListName(F_Util.color(
                        RankDisplay.rankColor(RankDisplay.Rank.EXECUTIVE) + "Executive " + P_CHATCOLOR + player.getName()));
            }
            
            else if (Ranks.isDeveloper(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.DEVELOPER, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.DEVELOPER, player);
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.DEVELOPER) + "Developer " + P_CHATCOLOR + player.getName()));
            }
            
            else if (Ranks.isOwner(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.OWNER, player);
                RankDisplay.rankJoinTag(RankDisplay.Rank.OWNER, player);
                player.setPlayerListName(
                        F_Util.color(RankDisplay.rankColor(RankDisplay.Rank.OWNER) + "Owner " + P_CHATCOLOR + player.getName()));
            }
    }
}
