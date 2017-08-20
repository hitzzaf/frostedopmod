package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_opall extends F_Command 
{
    public C_opall() 
    {
        super("opall", "/opall", "Ops all players on the server");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        
        if (!Rank.isAdmin(sender)) 
        {
            sender.sendMessage(NO_PERM);
            return false;
        }
        
        if (args.length == 0) 
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                player.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                player.sendMessage(OP);
                player.setOp(true);
            }
            return true;
        }
        return true;
    }
}