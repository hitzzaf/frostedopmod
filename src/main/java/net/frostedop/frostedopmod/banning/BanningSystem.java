package net.frostedop.frostedopmod.banning;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanningSystem 
{
    
    public static void addBan(Player player, CommandSender sender, String reason) 
    {
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".banner", sender.getName());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".reason", reason);
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", true);
        ConfigFiles.getBans().saveConfig();
    }
    
    public static void addBan(Player player, CommandSender sender) 
    {
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".name", player.getName());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".ip", player.getAddress().getHostString());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".banner", sender.getName());
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".reason", "No reason added!");
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", true);
        ConfigFiles.getBans().saveConfig();
    }
    
    public static void removeBan(Player player) 
    {
        ConfigEntry.BansConfig().set(player.getUniqueId().toString() + ".isbanned", false);
        ConfigFiles.getBans().saveConfig();
    }
    
    public static String getBanReason(Player player) 
    {
        return ConfigEntry.BansConfig().getString(player.getUniqueId().toString() + ".reason");
    }
    
    public static String getBanner(Player player) 
    {
        return ConfigEntry.BansConfig().getString(player.getUniqueId().toString() + ".banner");
    }
    
    public static boolean isBanned(Player player) 
    {
        return ConfigEntry.BansConfig().getBoolean(player.getUniqueId().toString() + ".isbanned");
    }
}
