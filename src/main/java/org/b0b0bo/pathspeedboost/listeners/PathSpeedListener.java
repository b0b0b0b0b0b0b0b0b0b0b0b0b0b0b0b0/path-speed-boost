package org.b0b0bo.pathspeedboost.listeners;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PathSpeedListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock();


        float playerDefaultSpeed = 0.2f;
        float playerBoostedSpeed = 0.8f; // Устанавливаем напрямую удвоенную скорость для тестирования

        player.sendMessage("Block: " + block.getType() + " Data: " + block.getData());
        player.sendMessage("Current walk speed: " + player.getWalkSpeed());

        if (block.getType() == Material.GRASS_PATH) {
            player.sendMessage("Setting boosted speed: " + playerBoostedSpeed);
            player.setWalkSpeed(playerBoostedSpeed);
        } else {
            player.sendMessage("Setting default speed: " + playerDefaultSpeed);
            player.setWalkSpeed(playerDefaultSpeed);
        }
    }
}
