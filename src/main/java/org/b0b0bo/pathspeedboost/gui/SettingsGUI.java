package org.b0b0bo.pathspeedboost.gui;

import org.b0b0bo.pathspeedboost.PathSpeedBoostPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class SettingsGUI {
    private final PathSpeedBoostPlugin plugin;

    public SettingsGUI(PathSpeedBoostPlugin plugin) {
        this.plugin = plugin;
    }

    public void openInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(new SettingsInventoryHolder(), 27, ChatColor.GOLD + PathSpeedBoostPlugin.getMessage("settings_title"));
        ((SettingsInventoryHolder) inventory.getHolder()).setInventory(inventory);
        initializeItems(inventory);
        player.openInventory(inventory);
    }

    private void initializeItems(Inventory inventory) {
        inventory.setItem(10, createGuiItem(Material.GRASS_PATH, ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("speed_multiplier"),
                ChatColor.YELLOW + "&eЛКМ: &fУвеличение",
                ChatColor.YELLOW + "&eПКМ: &fУменьшение",
                PathSpeedBoostPlugin.getMessage("current") + plugin.getSpeedMultiplier()));
        inventory.setItem(12, createGuiItem(Material.DIRT, ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier"),
                ChatColor.YELLOW + "&eЛКМ: &fУвеличение",
                ChatColor.YELLOW + "&eПКМ: &fУменьшение",
                PathSpeedBoostPlugin.getMessage("current") + plugin.getTallGrassSpeedMultiplier()));
        inventory.setItem(14, createGuiItem(Material.PAPER, ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("language"),
                PathSpeedBoostPlugin.getMessage("current") + plugin.getConfig().getString("language")));
        inventory.setItem(16, createGuiItem(Material.REDSTONE, ChatColor.RED + PathSpeedBoostPlugin.getMessage("reload_configuration")));
    }

    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> loreList = Arrays.asList(lore);
        for (int i = 0; i < loreList.size(); i++) {
            loreList.set(i, ChatColor.translateAlternateColorCodes('&', loreList.get(i)));
        }
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        boolean rightClick = event.isRightClick();
        boolean leftClick = event.isLeftClick();

        if (clickedItem.getItemMeta().getDisplayName().contains(PathSpeedBoostPlugin.getMessage("speed_multiplier"))) {
            float newMultiplier = plugin.getSpeedMultiplier() + (leftClick ? 0.1f : -0.1f);
            plugin.getConfig().set("speed-multiplier", newMultiplier);
            plugin.saveConfig();
            plugin.loadConfig();
            player.sendMessage(ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("speed_multiplier_updated") + newMultiplier);
        } else if (clickedItem.getItemMeta().getDisplayName().contains(PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier"))) {
            float newMultiplier = plugin.getTallGrassSpeedMultiplier() + (leftClick ? 0.1f : -0.1f);
            plugin.getConfig().set("tall-grass-speed-multiplier", newMultiplier);
            plugin.saveConfig();
            plugin.loadConfig();
            player.sendMessage(ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier_updated") + newMultiplier);
        } else if (clickedItem.getItemMeta().getDisplayName().contains(PathSpeedBoostPlugin.getMessage("language"))) {
            String currentLang = plugin.getConfig().getString("language");
            String newLang = currentLang.equals("en") ? "ru" : "en";
            plugin.getConfig().set("language", newLang);
            plugin.saveConfig();
            plugin.loadConfig();
            plugin.loadMessages();
            player.sendMessage(ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("language_updated") + newLang);
        } else if (clickedItem.getItemMeta().getDisplayName().contains(PathSpeedBoostPlugin.getMessage("reload_configuration"))) {
            plugin.reloadConfig();
            plugin.loadConfig();
            plugin.loadMessages();
            player.sendMessage(ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("configuration_reloaded"));
        }

        event.setCancelled(true);
        player.closeInventory();
        openInventory(player);  // Update the GUI with new values
    }
}
