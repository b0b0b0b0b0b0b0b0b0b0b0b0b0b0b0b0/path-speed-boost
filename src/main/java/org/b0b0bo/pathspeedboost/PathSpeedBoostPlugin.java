package org.b0b0bo.pathspeedboost;

import org.b0b0bo.pathspeedboost.commands.PathSpeedBoostCommand;
import org.b0b0bo.pathspeedboost.listeners.PlayerMoveListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class PathSpeedBoostPlugin extends JavaPlugin {

    private static float speedMultiplier;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);

        Objects.requireNonNull(getCommand("psb")).setExecutor(new PathSpeedBoostCommand(this));
        Objects.requireNonNull(getCommand("psb")).setTabCompleter(new PathSpeedBoostCommand(this));
    }

    public static float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void loadConfig() {
        speedMultiplier = (float) getConfig().getDouble("speed-multiplier", 1.4);  // Default to 1.4 for 40% increase
    }
}
