package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_deop extends FCommand {
        
    public C_deop() {
        super("deop", "/deop <player>", "De-op a certain player.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (args.length == 0) {
            return false;
        }
        
        Player player = (Player) sender;
        
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
        }
        
        FUtil.bcastMsg(ChatColor.AQUA + sender.getName() + " - De-opping " + player.getName());
        
        player.setOp(false);
        
        player.sendMessage(ChatColor.RED + "You are no longer OP!");
        
        return true;
}
}
