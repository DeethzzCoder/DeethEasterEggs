package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.configuration.exception.ConfigurationException;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class MainConfiguration extends AbstractConfiguration {

    private final Map<String, Object> settings = new HashMap<>();

    MainConfiguration(Plugin plugin) {
        super(plugin, "config");
        this.loadYaml();
        this.loadConfiguration();
    }
    
    @Override
    public void reload() {
        settings.clear();
        super.reload();
    }

    @Override
    protected void loadConfiguration() {
        configuration.getConfigurationSection("settings").getKeys(false).stream().forEach(path -> {
            loadSetting("settings." + path);
        });
    }

    private void loadSection(String pathSection) {
        configuration.getConfigurationSection(pathSection).getKeys(false).stream().forEach(path -> {
            loadSetting(pathSection + "." + path);
        });
    }

    private void loadSetting(String pathSetting) {
        if(configuration.isConfigurationSection(pathSetting)) {
            loadSection(pathSetting);
        } else {
            settings.put(pathSetting.replaceFirst("settings.", ""), configuration.get(pathSetting));
        }
    }

    public Object get(String path) {
        return settings.get(path);
    }

    public String getString(String path) {
        return (String) get(path);
    }

    public boolean getBoolean(String path) {
        return (boolean) get(path);
    }

    public List<String> getStringList(String path) {
        return (List<String>) get(path);
    }

    public TypeStorage getTypeStorage() throws ConfigurationException {
        return TypeStorage.fromString(getString("storage"));
    }

}
