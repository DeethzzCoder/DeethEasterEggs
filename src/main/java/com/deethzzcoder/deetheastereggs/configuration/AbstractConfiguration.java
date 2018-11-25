package com.deethzzcoder.deetheastereggs.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public abstract class AbstractConfiguration {

    protected final Plugin plugin;
    protected final File file;
    protected FileConfiguration configuration;

    protected AbstractConfiguration(Plugin plugin, String name) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), name + ".yml");
    }

    public void reload() {
        loadYaml();
        loadConfiguration();
    }

    protected void loadYaml() {
        if(!file.exists()) plugin.saveResource(file.getName(), false);
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    protected abstract void loadConfiguration();

}
