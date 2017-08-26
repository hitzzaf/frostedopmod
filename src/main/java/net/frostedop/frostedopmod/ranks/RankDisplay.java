package net.frostedop.frostedopmod.ranks;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import org.bukkit.entity.Player;

// More ranks to be added
public class RankDisplay {

    public enum Rank {
        IMPOSTER("a", "Imposter", "IMP", "&e"),
        SUPER_ADMIN("a", "Super Admin", "Super Admin", "&6"),
        TELNET_ADMIN("a", "Telnet Admin", "Telnet Admin", "&2"),
        SENIOR_ADMIN("a", "Senior Admin", "Senior Admin", "&d"),
        EXECUTIVE("a", "", "Executive", "&e"),
        DEVELOPER("a", "Developer", "Dev", "&5"),
        MASTER_BUILDER("a", "Master Builder", "Master Builder", "&6"),
        HITZZ("the", "Hitzz", "Hitzz", "&a"),
        OWNER("the", "Owner", "Owner", "&9");

        public final String lmsg_pre;
        public final String lmsg;
        public final String tag;
        public final String rankcolor;

        private Rank(String lmsg_pre, String lmsg, String tag, String rankcolor) {
            this.lmsg_pre = lmsg_pre;
            this.lmsg = lmsg;
            this.tag = tag;
            this.rankcolor = rankcolor;
        }
    }

    public static int rankJoin(Rank rank, Player player) {
        return FUtil.bcastMsg(FUtil.color("&b" + player.getName() + " is " + rank.lmsg_pre + rank.rankcolor + " &o " + rank.lmsg + "&b!"));
    }

    public static void rankJoinTag(Rank rank, Player player) {
        ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", "&8[" + rank.rankcolor + rank.tag + "&8]");
        ConfigFiles.getPlayer().saveConfig();
    }

    public static String rankColor(Rank rank) {
        return rank.rankcolor;
    }
}
