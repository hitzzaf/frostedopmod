package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_op extends FCommand {

    public C_op(String command) {
        super("op", "/op <player>", "Op someone!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (args.length == 1) {
            return false;
        }
        
        Player player = (Player) sender;

        FUtil.bcastMsg(ChatColor.AQUA + sender.getName() + " - Opping " + player.getName());
        
        player.setOp(true);
        
        return true;
    }
    
}
