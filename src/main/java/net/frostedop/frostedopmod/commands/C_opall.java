package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_opall extends FCommand {

    public C_opall() {
        super("opall", "/opall", "Ops all players on the server");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length == 0) {
            Bukkit.getOnlinePlayers().stream().forEach((player) -> {
                sender.sendMessage(ChatColor.AQUA + sender.getName() + " - Opping all players on the server!");
                sender.sendMessage(OP);
                player.setOp(true);
            });
            return true;
        }
        return true;
    }
}
