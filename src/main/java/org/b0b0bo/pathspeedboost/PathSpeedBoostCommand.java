package org.b0b0bo.pathspeedboost;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
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
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.loadConfig();
                sender.sendMessage(ChatColor.GREEN + "Path Speed Boost Plugin configuration reloaded.");
                return true;
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
            }
        }
        return suggestions;
    }
}
