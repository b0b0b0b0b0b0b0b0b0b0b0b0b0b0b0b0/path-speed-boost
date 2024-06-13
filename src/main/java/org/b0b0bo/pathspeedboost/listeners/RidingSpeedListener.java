package org.b0b0bo.pathspeedboost.listeners;

import org.b0b0bo.pathspeedboost.Main;
import org.b0b0bo.pathspeedboost.util.PlayerMoveUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RidingSpeedListener implements Listener {

    private final boolean isLegacyVersion;
    private final Material tallGrassMaterial;

    public RidingSpeedListener() {
        String version = Bukkit.getVersion();
        isLegacyVersion = version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12");

        if (isLegacyVersion) {
            tallGrassMaterial = Material.getMaterial("DOUBLE_PLANT");
        } else {
            tallGrassMaterial = Material.matchMaterial("TALL_GRASS");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!PlayerMoveUtils.shouldProcessEvent(event)) {
            return;
        }

        Player player = PlayerMoveUtils.getPlayer(event);
        Entity vehicle = player.getVehicle();

        if (vehicle instanceof Horse || vehicle instanceof Pig) {
            Block block = vehicle.getLocation().getBlock();

            float defaultSpeed = 0.2f;
            float boostedSpeed = defaultSpeed * Main.getMountSpeedMultiplier();
            float reducedSpeed = defaultSpeed * Main.getTallGrassReductionMultiplier();

            player.sendMessage("Block: " + block.getType() + " Data: " + block.getData());
            player.sendMessage("Mount Speed Multiplier from config: " + Main.getMountSpeedMultiplier());
            player.sendMessage("Current mount speed: " + ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue());

            if (block.getType() == Material.GRASS_PATH) {
                player.sendMessage("Setting boosted speed: " + boostedSpeed);
                if (vehicle instanceof Horse) {
                    ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(boostedSpeed);
                } else if (vehicle instanceof Pig) {
                    ((Pig) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(boostedSpeed);
                }
            } else if (isLegacyVersion) {
                if (block.getType() == tallGrassMaterial && block.getData() == 2) {
                    player.sendMessage("Setting reduced speed: " + reducedSpeed);
                    if (vehicle instanceof Horse) {
                        ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(reducedSpeed);
                    } else if (vehicle instanceof Pig) {
                        ((Pig) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(reducedSpeed);
                    }
                } else {
                    player.sendMessage("Setting default speed: " + defaultSpeed);
                    if (vehicle instanceof Horse) {
                        ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed);
                    } else if (vehicle instanceof Pig) {
                        ((Pig) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed);
                    }
                }
            } else {
                if (block.getType() == tallGrassMaterial) {
                    player.sendMessage("Setting reduced speed: " + reducedSpeed);
                    if (vehicle instanceof Horse) {
                        ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(reducedSpeed);
                    } else if (vehicle instanceof Pig) {
                        ((Pig) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(reducedSpeed);
                    }
                } else {
                    player.sendMessage("Setting default speed: " + defaultSpeed);
                    if (vehicle instanceof Horse) {
                        ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed);
                    } else if (vehicle instanceof Pig) {
                        ((Pig) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultSpeed);
                    }
                }
            }

            player.sendMessage("New mount speed: " + ((Horse) vehicle).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue());
        }
    }
}
