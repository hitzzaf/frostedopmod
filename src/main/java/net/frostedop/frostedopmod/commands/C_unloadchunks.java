/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey - help from TotalFreedom
 */
public class C_unloadchunks extends FCommand {

    public C_unloadchunks(String command) {
        super("unloadchunks", "/unloadchunks", "Unload chunks for the worlds");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        FUtil.bcastMsg(sender.getName() + " - Unloading unused chunks");

        int numChunks = 0;

        for (World world : Bukkit.getWorlds()) {
            numChunks += unloadUnusedChunks(world);
        }
        
        Player player = (Player) sender;
        
        player.sendMessage(numChunks + " chunks unloaded.");
        return true;
    }

    private int unloadUnusedChunks(World world) {
        int numChunks = 0;

        for (Chunk loadedChunk : world.getLoadedChunks()) {
            if (!world.isChunkInUse(loadedChunk.getX(), loadedChunk.getZ())) {
                if (world.unloadChunk(loadedChunk)) {
                    numChunks++;
                }
            }
        }

        return numChunks;
    }
 
}


