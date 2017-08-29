package net.frostedop.frostedopmod.listeners;

import static net.frostedop.frostedopmod.FUtil.color;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_CHATCOLOR;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_MUTED;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_TAG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FChatListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FChatListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();

        String tag = ConfigEntry.PlayerConfig().getString(UUID + P_TAG);
        String displayname = event.getPlayer().getDisplayName();
        String message = event.getMessage().trim();

        if (ConfigEntry.PlayerConfig().getBoolean(UUID + P_MUTED)) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't talk while muted!");
            event.setCancelled(true);
        }
        
        // ChatColor will be fixed at a later date <3
        if (tag == null) {
            String chatformat = color(
                    ChatColor.GRAY + displayname + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);
            event.setFormat(chatformat);
        } else {
            String chatformat = color(
                    tag + " " + ChatColor.GRAY + displayname + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY +  message);
            event.setFormat(chatformat);
        }
    }
}
