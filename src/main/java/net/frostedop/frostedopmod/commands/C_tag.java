package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_tag extends FCommand 
{
    public C_tag() 
    {
        super("tag", "/tag <set, remove> [tag]", "Set a tag, clear em or purgeall!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if (args.length == 0) {
            return false;
        }
        
        if (args.length == 1) {
            
            if (args[0].equals("off") && args[0].equals("remove"))
            {
                Player player = (Player) sender;
                
                sender.sendMessage(ChatColor.GRAY + "Your tag has been removed!");
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", null);
                ConfigFiles.getPlayer().reloadConfig();
                return true;
            }
            
            else if (args[0].equals("set")) {
                String string = StringUtils.join(args, " ");
                Player player = (Player) sender;
                
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", string);
                ConfigFiles.getPlayer().saveConfig();
                sender.sendMessage(ChatColor.GRAY + "Your tag was set to '" + FUtil.color(string) + ChatColor.GRAY + "'!");
                return true;
            }
        }
        return true;
    }
}