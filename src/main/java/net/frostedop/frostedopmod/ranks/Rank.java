package net.frostedop.frostedopmod.ranks;

import net.frostedop.frostedopmod.FUtil;
import org.bukkit.command.CommandSender;

public class Rank {

    public static boolean isAdmin(CommandSender sender) {
        return !(!Ranks.isSuperAdmin(sender) && !Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender));
    }

    public static boolean isTelnetAdmin(CommandSender sender) {
        return !(!Ranks.isTelnetAdmin(sender) && !Ranks.isSeniorAdmin(sender));
    }

    public static boolean isSeniorAdmin(CommandSender sender) {
        return Ranks.isSeniorAdmin(sender);
    }

    // Special ranks
    public static boolean isExecutive(CommandSender sender) {
        return !(!Ranks.isExecutive(sender) && !Ranks.isDeveloper(sender) && !Ranks.isOwner(sender));
    }
    
     public static boolean isHitzz(CommandSender sender) {
        return !(!Ranks.isHitzz(sender) && !Ranks.isDeveloper(sender) && !Ranks.isOwner(sender) && !Ranks.isSeniorAdmin(sender));
    }
    
    
    public static boolean isBuilder(CommandSender sender) {
        return FUtil.MASTERBUILDERS.contains(sender.getName());
    }
}
