package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import net.frostedop.frostedopmod.F_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_fopmr extends F_Command 
{
    
    public C_fopmr() 
    {
        super("fopmr", "/fopmr <reload>", "Main CMD ;D", Arrays.asList("fopm", "frostedopmodr"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    { 
        if (args.length == 1) 
        {
            
            if (!args[0].equals("reload")) 
            {
                return false;
            }

            if (!Rank.isAdmin(sender)) 
            {
                sender.sendMessage(NO_PERM);
                return true;
            }
            
            ConfigFiles.getAdmins().reloadConfig();
            ConfigFiles.getDonators().reloadConfig();
            ConfigFiles.getMConfig().reloadConfig();
            ConfigFiles.getPlayer().reloadConfig();
            sender.sendMessage(ChatColor.GRAY + "FrostedOPMod: Remastered has been reloaded!");
            return true;
        }
        
        if (args.length == 0) 
        {
            sender.sendMessage(F_Util.color("&8&m----------------------"));
            sender.sendMessage(ChatColor.BLUE + "FrostedOPMod: Remastered");
            sender.sendMessage(ChatColor.BLUE + "Version: " + FrostedOPMod.plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.BLUE + "Developers: " + FrostedOPMod.plugin.getDescription().getAuthors());
            sender.sendMessage(ChatColor.BLUE + "Last developed by: " + "Sinkable [Savnith]");
            sender.sendMessage(F_Util.color("&8&m----------------------"));
            return true;
        }
        return true;
    }
}

