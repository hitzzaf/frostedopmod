package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.ranks.RankDisplay;
import net.frostedop.frostedopmod.ranks.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FRankListener implements Listener {
    
    FrostedOPMod plugin;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public FRankListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();

        final Player player = event.getPlayer();
            
            // Login Messages Handler
            if (Ranks.isSuperAdmin(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.SUPER_ADMIN, player);
            }else if (Ranks.isTelnetAdmin(player)) { 
                RankDisplay.rankJoin(RankDisplay.Rank.TELNET_ADMIN, player);
            }else if (Ranks.isSeniorAdmin(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.SENIOR_ADMIN, player);
            }else if (Ranks.isExecutive(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.EXECUTIVE, player);
            }else if (Ranks.isDeveloper(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.DEVELOPER, player);
            }else if (Ranks.isOwner(player)) {
                RankDisplay.rankJoin(RankDisplay.Rank.OWNER, player);
            }
            
            // Tag Handler
            if (Ranks.isSuperAdmin(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.SUPER_ADMIN, player);
            }else if (Ranks.isTelnetAdmin(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.TELNET_ADMIN, player);
            }else if (Ranks.isSeniorAdmin(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.SENIOR_ADMIN, player);
            }else if (Ranks.isExecutive(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.EXECUTIVE, player);
            }else if (Ranks.isDeveloper(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.DEVELOPER, player);
            }else if (Ranks.isOwner(player)) {
                RankDisplay.rankJoinTag(RankDisplay.Rank.OWNER, player);
            }
            
            // PlayerList Handler
            if (Ranks.isSuperAdmin(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.SUPER_ADMIN) + "SA " + player.getName()));
            }else if (Ranks.isTelnetAdmin(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.TELNET_ADMIN) + "STA " + player.getName()));
            }else if (Ranks.isSeniorAdmin(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.SENIOR_ADMIN) + "SrA " + player.getName()));
            }else if (Ranks.isExecutive(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.EXECUTIVE) + "EXE " + player.getName()));
            }else if (Ranks.isDeveloper(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.DEVELOPER) + "Dev " + player.getName()));
            }else if (Ranks.isOwner(player)) {
                player.setPlayerListName(FUtil.color(RankDisplay.rankColor(RankDisplay.Rank.OWNER) + "Owner " + player.getName()));
            }
    }
}
