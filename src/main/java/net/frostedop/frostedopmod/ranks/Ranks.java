package net.frostedop.frostedopmod.ranks;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ranks {

    // Admin Section
    public static boolean isImposter(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.PlayerConfig().getBoolean(player.getUniqueId().toString() + ".imposter");
    }

    public static boolean isSuperAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin");
    }

    public static boolean isTelnetAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".istelnet");
    }

    public static boolean isSeniorAdmin(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".issenior");
    }

    public static boolean isExecutive(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".executive");
    }

    public static boolean isOwner(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".owner");
    }

    public static boolean isDeveloper(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".developer");
    }

    // Builder ranks
    public static boolean isMasterBuilder(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return FUtil.MASTERBUILDERS.contains(player.getName());
    }

    // player section
    public static boolean isOp(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        }

        final Player player = (Player) sender;

        return !ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin");
    }
}
