package org.b0b0bo.pathspeedboost.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveUtils {

    public static Location getFrom(PlayerMoveEvent event) {
        return event.getFrom();
    }

    public static Location getTo(PlayerMoveEvent event) {
        return event.getTo();
    }

    public static boolean hasMoved(Location from, Location to) {
        return from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ();
    }

    public static Player getPlayer(PlayerMoveEvent event) {
        return event.getPlayer();
    }

    public static Block getBlock(Player player) {
        return player.getLocation().getBlock();
    }

    public static float getDefaultSpeed() {
        return 0.2f;
    }

    public static boolean shouldProcessEvent(PlayerMoveEvent event) {
        Location from = getFrom(event);
        Location to = getTo(event);
        return hasMoved(from, to);
    }
}
