package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.utility.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class ReloadSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final Plugin plugin;

    ReloadSubCommand(LanguageConfiguration languageConfiguration, Plugin plugin) {
        super("reload", Arrays.asList("rel", "restart", "res"), true, 0, 0);
        this.languageConfiguration = languageConfiguration;
        this.plugin = plugin;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        LoggerUtils.warn("&cReloading the plugin....");
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(plugin);
        pluginManager.enablePlugin(plugin);
        LoggerUtils.warn("&aThe plugin was successfully reloaded!");
        languageConfiguration.getMessage("reload-subcommand.successfully").send(sender);
    }

}
