package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.utility.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class ReloadSubCommand extends AbstractSubCommand {

    private final Logger logger = Logger.getLogger("ReloadSubCommand");
    private final LanguageConfiguration languageConfiguration;
    private final Plugin plugin;

    ReloadSubCommand(LanguageConfiguration languageConfiguration, Plugin plugin) {
        super("reload", Arrays.asList("rel", "restart", "res"), true, 0, 0);
        this.languageConfiguration = languageConfiguration;
        this.plugin = plugin;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        logger.warning(ColorUtils.color("&cReloading the plugin...."));
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(plugin);
        pluginManager.enablePlugin(plugin);
        logger.warning(ColorUtils.color("&aThe plugin was successfully reloaded!"));
        languageConfiguration.getMessage("reload-subcommand.successfully").send(sender);
    }

}
