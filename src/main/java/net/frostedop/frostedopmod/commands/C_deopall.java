package net.frostedop.frostedopmod.commands;

import java.util.Collection;
import static net.frostedop.frostedopmod.commands.F_Command.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_deopall extends F_Command 
{
    
    public C_deopall() 
    {
        super("deopall", "/deopall", "De-ops all players on the server");
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
                player.sendMessage(ChatColor.AQUA + sender.getName() + " - De-opping all players on the server!");
                player.sendMessage(DEOP);
                player.setOp(false);
            }
            return true;
        }
        return true;
    }
}
