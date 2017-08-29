package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.bridge.WorldEditBridge;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Savnith
 */
// Make this in to a GUI? So the admins can pick the default limit.
public class C_setlimit extends FCommand {

    public C_setlimit() {
        super("setlimit", "/setlimit", "A worldedit command.", Arrays.asList("setl"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length == 0) {
            Bukkit.getOnlinePlayers().stream().map((player) -> {
                player.sendMessage(ChatColor.RED + sender.getName() + " - " + "Setting everyone's Worldedit block modification limit to 10000.");
                return player;
            }).forEach((player) -> {
                WorldEditBridge.setLimit(player, 10000);
            });
        }
        return true;
    }
}
