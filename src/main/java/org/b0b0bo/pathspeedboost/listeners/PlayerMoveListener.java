package org.b0b0bo.pathspeedboost.listeners;

import org.b0b0bo.pathspeedboost.PathSpeedBoostPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (!hasMoved(from, to)) {
            return;
        }

        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();

        float defaultSpeed = 0.2f;
        float boostedSpeed = defaultSpeed * PathSpeedBoostPlugin.getSpeedMultiplier();

        if (block.getType() == Material.DIRT_PATH) {
            player.setWalkSpeed(boostedSpeed);
        } else {
            player.setWalkSpeed(defaultSpeed);
        }
    }

    private boolean hasMoved(Location from, Location to) {
        return from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ();
    }
}
