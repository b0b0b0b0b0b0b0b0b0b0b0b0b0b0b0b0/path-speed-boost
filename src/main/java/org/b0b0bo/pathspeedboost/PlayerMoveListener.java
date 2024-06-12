package org.b0b0bo.pathspeedboost;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
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
}
