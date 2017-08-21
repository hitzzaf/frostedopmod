package net.frostedop.frostedopmod.events;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LoggingEvent 
{
    public static void mainLogging(CommandSender sender, String reason) 
    {
        ConfigEntry.LogConfig().set(((Player)sender).getUniqueId().toString() + ".name", sender.getName());
        ConfigEntry.LogConfig().set(((Player)sender).getUniqueId().toString() + ".ip", ((Player) sender).getAddress().getHostString());
        ConfigEntry.LogConfig().set(((Player)sender).getUniqueId().toString() + ".reason", reason);
        ConfigFiles.getLogs().saveConfig();
    }
}
