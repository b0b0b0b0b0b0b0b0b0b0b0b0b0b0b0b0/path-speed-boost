package org.b0b0bo.pathspeedboost;

import org.b0b0bo.pathspeedboost.commands.PathSpeedBoostCommand;
import org.b0b0bo.pathspeedboost.listeners.PlayerMoveListener;
import org.b0b0bo.pathspeedboost.gui.SettingsGUI;
import org.b0b0bo.pathspeedboost.gui.SettingsInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PathSpeedBoostPlugin extends JavaPlugin implements Listener {

    private static float speedMultiplier;
    private static float tallGrassSpeedMultiplier;
    private static String language;
    private static Map<String, String> messages = new HashMap<>();
    private static final int CONFIG_VERSION = 1;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        checkConfigVersion(); // Ensure the config version is checked after loading config
        loadMessages();
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(this, this);

        Objects.requireNonNull(getCommand("psb")).setExecutor(new PathSpeedBoostCommand(this));
        Objects.requireNonNull(getCommand("psb")).setTabCompleter(new PathSpeedBoostCommand(this));

        logPluginInfo();
    }

    public static float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public static float getTallGrassSpeedMultiplier() {
        return tallGrassSpeedMultiplier;
    }

    public static String getMessage(String key) {
        return messages.getOrDefault(key, key);
    }

    public void loadConfig() {
        try {
            speedMultiplier = (float) getConfig().getDouble("speed-multiplier", 1.4);  // Default to 1.4 for 40% increase
            tallGrassSpeedMultiplier = (float) getConfig().getDouble("tall-grass-speed-multiplier", 0.5);  // Default to 0.5 for 50% decrease
            language = getConfig().getString("language", "en");
        } catch (Exception e) {
            getLogger().severe("Failed to load configuration.");
        }
    }

    public void loadMessages() {
        File messagesFile = new File(getDataFolder(), "messages_" + language + ".yml");
        if (!messagesFile.exists()) {
            saveResource("messages_" + language + ".yml", false);
        }

        FileConfiguration messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        for (String key : messagesConfig.getKeys(false)) {
            messages.put(key, messagesConfig.getString(key));
        }
    }

    private void checkConfigVersion() {
        FileConfiguration config = getConfig();
        int currentVersion = config.getInt("version", 0);
        if (currentVersion < CONFIG_VERSION) {
            saveResource("config.yml", true);
            saveResource("messages_en.yml", true);
            saveResource("messages_ru.yml", true);
            reloadConfig();
            loadConfig(); // Reload configuration values
            loadMessages();
            getLogger().info("Configuration files updated to version " + CONFIG_VERSION);
        }
    }

    private void logPluginInfo() {
        Bukkit.getLogger().info("\u001B[30;1m================\u001B[37;1mBM\u001B[30;1m===================\u001B[0m");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("\u001B[32mSpeed Multiplier: " + "\u001B[92m" + speedMultiplier + "\u001B[0m");
        Bukkit.getLogger().info("\u001B[32mTall Grass Speed Multiplier: " + "\u001B[92m" + tallGrassSpeedMultiplier + "\u001B[0m");
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info("\u001B[30;1m-------------------------------------\u001B[0m");
        Bukkit.getLogger().info("\u001B[35mPath Speed Boost Plugin enabled\u001B[0m");
        Bukkit.getLogger().info("\u001B[30;1m=====================================\u001B[0m");
    }




    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof SettingsInventoryHolder) {
            SettingsGUI settingsGUI = new SettingsGUI(this);
            settingsGUI.handleInventoryClick(event);
        }
    }
}
