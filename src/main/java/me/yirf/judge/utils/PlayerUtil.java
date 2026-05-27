package me.yirf.judge.utils;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public final class PlayerUtil {

    private static final String VANISHED_METADATA = "vanished";

    private PlayerUtil() {
    }

    public static boolean isVanished(Player player) {
        if (!player.hasMetadata(VANISHED_METADATA)) {
            return false;
        }

        for (MetadataValue metadata : player.getMetadata(VANISHED_METADATA)) {
            if (metadata.asBoolean()) {
                return true;
            }
        }
        return false;
    }
}
