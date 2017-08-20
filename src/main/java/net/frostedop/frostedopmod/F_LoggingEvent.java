package net.frostedop.frostedopmod;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class F_LoggingEvent 
{
    public static void mainLogging(CommandSender sender, String reason) 
    {
        ConfigEntry.BansConfig().set(((Player)sender).getUniqueId().toString() + ".name", sender.getName());
        ConfigEntry.BansConfig().set(((Player)sender).getUniqueId().toString() + ".ip", ((Player) sender).getAddress().getHostString());
        ConfigEntry.BansConfig().set(((Player)sender).getUniqueId().toString() + ".reason", reason);
        ConfigFiles.getBans().saveConfig();
    }
}
