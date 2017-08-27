package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import static net.frostedop.frostedopmod.commands.FCommand.PLAYER_NOT_FOUND;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey & savnith
 */
public class C_warn extends FCommand {

    public C_warn() {
        super("warn", "/warn <player> <reason>", "Warn a player for being bad.");
    }

    @Override
    @SuppressWarnings("null")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length < 1) {
            return false;
        }

        final Player player = Bukkit.getServer().getPlayer(args[0]);

        String reason = null;

        if (args.length > 1) {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        if (player == null) {
            sender.sendMessage(PLAYER_NOT_FOUND);
            return true;
        }

        warn(player, sender, reason);
        return true;
    }

    public static void warn(Player player, CommandSender sender, String reason) {
        
        if (reason != null) {
            sender.sendMessage(ChatColor.RED + "You have successfully warned, " + player.getName() + " for (" + reason + ").");
            player.sendMessage(ChatColor.RED + "You have been warned by " + sender.getName() + " for (" + reason + ").");
        } else {
            sender.sendMessage(ChatColor.GRAY + "Please provide a warn reason.");
        }
    }
}
