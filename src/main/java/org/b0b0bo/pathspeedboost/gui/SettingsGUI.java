package org.b0b0bo.pathspeedboost.gui;

import java.util.Arrays;
import java.util.List;
import org.b0b0bo.pathspeedboost.PathSpeedBoostPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsGUI {
    private final PathSpeedBoostPlugin plugin;

    public SettingsGUI(PathSpeedBoostPlugin plugin) {
        this.plugin = plugin;
    }

    public void openInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(new SettingsInventoryHolder(), 27, ChatColor.GOLD + PathSpeedBoostPlugin.getMessage("settings_title"));
        ((SettingsInventoryHolder) inventory.getHolder()).setInventory(inventory);
        this.initializeItems(inventory);
        player.openInventory(inventory);
    }

    private void initializeItems(Inventory inventory) {
        Material var10003 = Material.GRASS_PATH;
        String var10004 = ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("speed_multiplier");
        String[] var10005 = new String[]{PathSpeedBoostPlugin.getMessage("left_click_increase"), PathSpeedBoostPlugin.getMessage("right_click_decrease"), null};
        StringBuilder var10008 = (new StringBuilder()).append(PathSpeedBoostPlugin.getMessage("current"));
        PathSpeedBoostPlugin var10009 = this.plugin;
        var10005[2] = var10008.append(PathSpeedBoostPlugin.getSpeedMultiplier()).toString();
        inventory.setItem(10, this.createGuiItem(var10003, var10004, var10005));
        var10003 = Material.DIRT;
        var10004 = ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier");
        var10005 = new String[]{PathSpeedBoostPlugin.getMessage("left_click_increase"), PathSpeedBoostPlugin.getMessage("right_click_decrease"), null};
        var10008 = (new StringBuilder()).append(PathSpeedBoostPlugin.getMessage("current"));
        var10009 = this.plugin;
        var10005[2] = var10008.append(PathSpeedBoostPlugin.getTallGrassSpeedMultiplier()).toString();
        inventory.setItem(12, this.createGuiItem(var10003, var10004, var10005));
        inventory.setItem(14, this.createGuiItem(Material.PAPER, ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("language"), PathSpeedBoostPlugin.getMessage("current") + this.plugin.getConfig().getString("language")));
        inventory.setItem(16, this.createGuiItem(Material.REDSTONE, ChatColor.RED + PathSpeedBoostPlugin.getMessage("reload_configuration")));
    }

    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> loreList = Arrays.asList(lore);

        for(int i = 0; i < loreList.size(); ++i) {
            loreList.set(i, ChatColor.translateAlternateColorCodes('&', loreList.get(i)));
        }

        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    public void handleInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && clickedItem.getType() != Material.AIR) {
            boolean rightClick = event.isRightClick();
            boolean leftClick = event.isLeftClick();
            PathSpeedBoostPlugin var10000;
            float newMultiplier;
            if (event.getSlot() == 10) {
                var10000 = this.plugin;
                newMultiplier = PathSpeedBoostPlugin.getSpeedMultiplier() + (leftClick ? 0.1F : -0.1F);
                this.plugin.getConfig().set("speed-multiplier", newMultiplier);
                this.plugin.saveConfig();
                this.plugin.loadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("speed_multiplier_updated") + newMultiplier));
            } else if (event.getSlot() == 12) {
                var10000 = this.plugin;
                newMultiplier = PathSpeedBoostPlugin.getTallGrassSpeedMultiplier() + (leftClick ? 0.1F : -0.1F);
                this.plugin.getConfig().set("tall-grass-speed-multiplier", newMultiplier);
                this.plugin.saveConfig();
                this.plugin.loadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier_updated") + newMultiplier));
            } else if (event.getSlot() == 14) {
                String currentLang = this.plugin.getConfig().getString("language");
                String newLang = currentLang.equals("en") ? "ru" : "en";
                this.plugin.getConfig().set("language", newLang);
                this.plugin.saveConfig();
                this.plugin.loadConfig();
                this.plugin.loadMessages();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("language_updated") + newLang));
            } else if (event.getSlot() == 16) {
                this.plugin.reloadConfig();
                this.plugin.loadConfig();
                this.plugin.loadMessages();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("configuration_reloaded")));
            }

            event.setCancelled(true);
            player.closeInventory();
            this.openInventory(player);
        }
    }
}
