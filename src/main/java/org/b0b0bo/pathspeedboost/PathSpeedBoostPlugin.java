package org.b0b0bo.pathspeedboost;

import org.bukkit.plugin.java.JavaPlugin;

public class PathSpeedBoostPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
    }
}