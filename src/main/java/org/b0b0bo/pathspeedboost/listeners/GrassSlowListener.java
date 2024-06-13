package org.b0b0bo.pathspeedboost.listeners;

import org.b0b0bo.pathspeedboost.Main;
import org.b0b0bo.pathspeedboost.util.PlayerMoveUtils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GrassSlowListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!PlayerMoveUtils.shouldProcessEvent(event)) {
            return;
        }

        Player player = PlayerMoveUtils.getPlayer(event);
        Block block = PlayerMoveUtils.getBlock(player);

        float defaultSpeed = PlayerMoveUtils.getDefaultSpeed();
        float reducedSpeed = defaultSpeed * Main.getTallGrassReductionMultiplier();

        //player.sendMessage("Block: " + block.getType() + " Data: " + block.getData());

            if ((block.getType() == Material.getMaterial("DOUBLE_PLANT") && block.getData() == 2) || (block.getType() == Material.getMaterial("TALL_GRASS") && block.getData() == 2)) {
                player.setWalkSpeed(reducedSpeed);
            } else {
                player.setWalkSpeed(defaultSpeed);
            }

    }
}
