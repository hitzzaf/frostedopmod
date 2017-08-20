package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import net.frostedop.frostedopmod.F_Util;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_say extends F_Command 
{
    public C_say() 
    {
        super("say", "/say <message>", "A simple admin command!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
        if (!Rank.isAdmin(sender)) 
        {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length == 0) {
            return false;
        }
        
        String message = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");
        
        F_Util.bcastMsg(F_Util.color(ConfigEntry.AdminConfig().getString(((Player) sender).getUniqueId().toString() + ".shoutcolor") + 
                        "[Server:" + sender.getName() + "] " + message));
        return true;
    }
}