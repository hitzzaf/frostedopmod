package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author hailey
 */
public class C_stop extends FCommand {

    public C_stop(String command) {
        super("stop", "/stop", "Stop the server.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length < 1) {
            return false;
        }

        FUtil.bcastMsg(ChatColor.RED + "Server is restarting - You will be kicked in 5 seconds!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        Bukkit.shutdown();

        return true;
    }

}
