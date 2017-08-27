package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_warn extends FCommand {
        
    public C_warn() {
        super("warn", "/warn <player> <reason>", "Warn a player for being bad.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length == 0) {
            return false;
        }
        
        Player player = (Player) sender;
        
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
        }
        
        final String warning = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "You have been warned by an admin for " + warning); 
        
        sender.sendMessage(ChatColor.RED + "You have warned " + player.getName() + " for: " + warning);
        
        return true;
}
}
