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

        if (!hasPlayerMoved(from, to)) {
            return;
        }

        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();

        float defaultSpeed = 0.2f;
        player.setWalkSpeed(defaultSpeed);

        handlePathSpeed(player, block);
        handleTallGrassSpeed(player, block);
    }

    private void handlePathSpeed(Player player, Block block) {
        float boostedSpeed = 0.2f * PathSpeedBoostPlugin.getSpeedMultiplier();

        if (block.getType().toString().contains("GRASS_PATH")) {
            player.setWalkSpeed(boostedSpeed);
            //player.sendMessage("Block: " + block.getType() + " Data: " + block.getData());
        }
    }

    private void handleTallGrassSpeed(Player player, Block block) {
        float reducedSpeed = 0.2f * PathSpeedBoostPlugin.getTallGrassSpeedMultiplier();
        // player.sendMessage("Tall Grass Block: " + block.getType() + " Data: " + block.getData());
        if ((block.getType() == Material.getMaterial("DOUBLE_PLANT")) ||
                (block.getType() == Material.getMaterial("TALL_GRASS") && block.getData() == 2)) {
            player.setWalkSpeed(reducedSpeed);
            //player.sendMessage("Tall Grass Block: " + block.getType() + " Data: " + block.getData());
        }
    }

    private boolean hasPlayerMoved(Location from, Location to) {
        return from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ();
    }
}
