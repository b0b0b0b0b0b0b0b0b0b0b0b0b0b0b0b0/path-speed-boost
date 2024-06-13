package org.b0b0bo.pathspeedboost;

import org.b0b0bo.pathspeedboost.commands.MainCommand;
import org.b0b0bo.pathspeedboost.listeners.PathSpeedListener;
import org.b0b0bo.pathspeedboost.listeners.GrassSlowListener;
import org.b0b0bo.pathspeedboost.listeners.RidingSpeedListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static float playerSpeedMultiplier;
    private static float mountSpeedMultiplier;
    private static float tallGrassReductionMultiplier;
    private static boolean enablePathSpeedBoost;
    private static boolean enableTallGrassSpeedReduction;
    private static boolean enableMountPathSpeedBoost;
    private static final Logger logger = Logger.getLogger("PathSpeedBoost");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        if (enablePathSpeedBoost) {
            getServer().getPluginManager().registerEvents(new PathSpeedListener(), this);
        }
        if (enableTallGrassSpeedReduction) {
            getServer().getPluginManager().registerEvents(new GrassSlowListener(), this);
        }
        if (enableMountPathSpeedBoost) {
            getServer().getPluginManager().registerEvents(new RidingSpeedListener(), this);
        }

        Objects.requireNonNull(getCommand("psb")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(getCommand("psb")).setTabCompleter(new MainCommand(this));

        logger.info("==========================================");
        logger.info("Path Speed Boost Plugin enabled!");
        logger.info("Configuration loaded:");
        logger.info("  Player Speed Multiplier: " + playerSpeedMultiplier);
        logger.info("  Mount Speed Multiplier: " + mountSpeedMultiplier);
        logger.info("  Tall Grass Reduction Multiplier: " + tallGrassReductionMultiplier);
        logger.info("  Path Speed Boost Enabled: " + enablePathSpeedBoost);
        logger.info("  Tall Grass Speed Reduction Enabled: " + enableTallGrassSpeedReduction);
        logger.info("  Mount Path Speed Boost Enabled: " + enableMountPathSpeedBoost);
        logger.info("==========================================");
    }

    public static float getPlayerSpeedMultiplier() {
        return playerSpeedMultiplier;
    }

    public static float getMountSpeedMultiplier() {
        return mountSpeedMultiplier;
    }

    public static float getTallGrassReductionMultiplier() {
        return tallGrassReductionMultiplier;
    }

    public static boolean isEnableMountPathSpeedBoost() {
        return enableMountPathSpeedBoost;
    }

    public void loadConfig() {
        playerSpeedMultiplier = (float) getConfig().getDouble("player-speed-multiplier", 1.4);  // Default to 1.4 for 40% increase
        mountSpeedMultiplier = (float) getConfig().getDouble("mount-speed-multiplier", 1.4);  // Default to 1.4 for 40% increase
        tallGrassReductionMultiplier = (float) getConfig().getDouble("tall-grass-reduction-multiplier", 0.5);  // Default to 0.5 for 50% reduction
        enablePathSpeedBoost = getConfig().getBoolean("enable-path-speed-boost", true);  // Default to true
        enableTallGrassSpeedReduction = getConfig().getBoolean("enable-tall-grass-speed-reduction", true);  // Default to true
        enableMountPathSpeedBoost = getConfig().getBoolean("enable-mount-path-speed-boost", true);  // Default to true
    }
}
