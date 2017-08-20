package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class C_flatlands extends F_Command {
    
    public C_flatlands() 
    {
        super("flatlands", "/flatlands", "Go to the flatlands!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
        
        
        if (isConsole()) {
            sender.sendMessage("You must be ingame to use this command!");
        }
        
        //if (((Player) sender).getWorld() == WorldManager.getFlatlands()) {
        //    ((Player) sender).teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        //    sender.sendMessage("Welcome to the main world!" + ChatColor.GRAY);
        //    return true;
        //}try {
        //    ((Player) sender).teleport(WorldManager.getFlatlands().getSpawnLocation());
        //    sender.sendMessage("Welcome to the flatlands!" + ChatColor.GRAY);
        //}catch(Exception ex) {
        //    sender.sendMessage("The flatlands can not be accessable atm, if it doesn't work after a few mins plese contact Savnith(KobeTricee) on discord or In-game!");
        //}
        return true;
    }  
}
