package org.b0b0bo.pathspeedboost.commands;

import org.b0b0bo.pathspeedboost.PathSpeedBoostPlugin;
import org.b0b0bo.pathspeedboost.gui.SettingsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PathSpeedBoostCommand implements CommandExecutor, TabCompleter {

    private final PathSpeedBoostPlugin plugin;

    public PathSpeedBoostCommand(PathSpeedBoostPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("psb")) {
            if (!sender.hasPermission("pathspeedboost.admin")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        plugin.reloadConfig();
                        plugin.loadConfig();
                        plugin.loadMessages();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("prefix") + ChatColor.GREEN + PathSpeedBoostPlugin.getMessage("config_reloaded")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("prefix") + PathSpeedBoostPlugin.getMessage("speed_multiplier") + PathSpeedBoostPlugin.getSpeedMultiplier()));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("prefix") + PathSpeedBoostPlugin.getMessage("tall_grass_speed_multiplier") + PathSpeedBoostPlugin.getTallGrassSpeedMultiplier()));
                    } catch (Exception e) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PathSpeedBoostPlugin.getMessage("prefix") + ChatColor.RED + PathSpeedBoostPlugin.getMessage("reload_failed")));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("gui")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        SettingsGUI settingsGUI = new SettingsGUI(plugin);
                        settingsGUI.openInventory(player);
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("psb")) {
            if (args.length == 1) {
                if ("reload".startsWith(args[0].toLowerCase())) {
                    suggestions.add("reload");
                }
                if ("gui".startsWith(args[0].toLowerCase())) {
                    suggestions.add("gui");
                }
            }
        }
        return suggestions;
    }
}
