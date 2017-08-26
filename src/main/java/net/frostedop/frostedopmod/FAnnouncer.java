package net.frostedop.frostedopmod;

import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.A_DELAY;
import static net.frostedop.frostedopmod.config.ConfigEntry.A_MESSAGES;
import static net.frostedop.frostedopmod.config.ConfigEntry.A_PREFIX;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FAnnouncer {

    public static void Broadcast(Plugin pl) {
        new BukkitRunnable() {
            int number = 0;

            @Override
            public void run() {
                if (this.number >= ConfigEntry.MainConfig().getStringList(A_MESSAGES).size()) {
                    this.number = 0;
                }

                String prefix = ConfigEntry.MainConfig().getString(A_PREFIX);
                String message_color = ConfigEntry.MainConfig().getString("announcer.message-color");
                String message = (String) ConfigEntry.MainConfig().getStringList(A_MESSAGES).get(this.number);

                this.number += 1;

                FUtil.bcastMsg(FUtil.color(prefix + " " + message_color + message));
            }
        }.runTaskTimer(pl, 100L, 20 * pl.getConfig().getInt(A_DELAY));
    }
}
