package net.frostedop.frostedopmod.ranks;

import net.frostedop.frostedopmod.FUtil;
import org.bukkit.command.CommandSender;

public class Rank {
    
    public static boolean isAdmin(CommandSender sender) {
        if (!Ranks.isSuperAdmin(sender) && !Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isTelnetAdmin(CommandSender sender) {
        if (!Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isSeniorAdmin(CommandSender sender) {
        if (!Ranks.isSeniorAdmin(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    // Special ranks
    public static boolean isExecutive(CommandSender sender) {
        if (!Ranks.isExecutive(sender)  && !Ranks.isDeveloper(sender) && !Ranks.isOwner(sender)) {
            return false;
        }else {
            return true;
        }
    }
    
    public static boolean isBuilder(CommandSender sender) {
        if (!FUtil.MASTERBUILDERS.contains(sender.getName())) {
            return false;
        }else {
            return true;
        }
    }
}
