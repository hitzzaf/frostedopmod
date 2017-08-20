package net.frostedop.frostedopmod.commands;

import java.util.Collection;
import net.frostedop.frostedopmod.F_Util;
import static net.frostedop.frostedopmod.commands.F_Command.DEOP;
import static net.frostedop.frostedopmod.commands.F_Command.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_rawsay extends F_Command 
{
    
    public C_rawsay() 
    {
        super("rawsay", "/rawsay <message>", "raw msg");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        
        if (!Rank.isSeniorAdmin(sender)) 
        {
            sender.sendMessage(NO_PERM);
            return false;
        }
        
        Collection<Player> online = (Collection<Player>) Bukkit.getOnlinePlayers();
        final String message = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        
        if (args.length == 0) 
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                player.sendMessage(F_Util.color(message));
            }
            return true;
        }
        return true;
    }
}
