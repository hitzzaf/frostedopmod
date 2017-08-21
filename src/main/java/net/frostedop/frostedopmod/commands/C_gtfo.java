package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.events.BanningEvent;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import net.frostedop.frostedopmod.FUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class C_gtfo extends FCommand 
{
    
    public C_gtfo() 
    {
        super("gtfo", "/gtfo <playername> [reason]", "Ban someone!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        
        if (!Rank.isAdmin(sender)) 
        {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length == 0) 
        {
            return false;
        }
        
        final String reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        Player target = Bukkit.getServer().getPlayer(args[0]);
        
        if (args.length == 1)
        {
            if (target == null)
            {
                sender.sendMessage(PLAYER_NOT_FOUND);
                return false;
            }
            
            FUtil.bcastMsg(ChatColor.RED + target.getName() + " has been a VERY naughty, naughty boy. \nBanning: " + target.getName() + ", IP: " 
                    + ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip"));
            target.kickPlayer(ChatColor.RED + "GTFO");
            BanningEvent.addBan(target, sender);
            Bukkit.getServer().dispatchCommand(sender, "co rb u:" + target.getName() + " t:24h r:global #silent");
        }
        
        if (args.length > 1) 
        {
            if (reason != null) 
            {
                FUtil.bcastMsg(ChatColor.RED + target.getName() + " has been a VERY naughty, naughty boy. \nBanning: " + target.getName() + ", IP: " 
                    + ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip") + "\nReason: " + ChatColor.GOLD + reason);
                target.kickPlayer(ChatColor.RED + "GTFO");
                BanningEvent.addBan(target, sender, reason);
                Bukkit.getServer().dispatchCommand(sender, "co rb u:" + target.getName() + " t:24h r:global #silent");
            }
        }
        return true;
    }
}