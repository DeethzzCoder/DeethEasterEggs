package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.configuration.exception.ConfigurationException;
import com.deethzzcoder.deetheastereggs.utility.StringUtils;
import com.deethzzcoder.deetheastereggs.utility.TextComponentBuilder;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class LanguageConfiguration extends AbstractConfiguration {

    private final Map<String, Message> messages = new HashMap<>();

    LanguageConfiguration(Plugin plugin) {
        super(plugin, "language");
        this.loadYaml();
        this.loadConfiguration();
    }

    @Override
    public void reload() {
        messages.clear();
        super.reload();
    }

    @Override
    protected void loadConfiguration() {
        configuration.getConfigurationSection("messages").getKeys(false).stream().forEach(path -> {
            loadSetting("messages." + path);
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
        } else if(configuration.isString(pathSetting)) {
            messages.put(pathSetting, new Message(new TextComponentBuilder(configuration.getString(pathSetting)).build()));
        } else if(configuration.isList(pathSetting)) {
            messages.put(pathSetting, new Message(new TextComponentBuilder(StringUtils.mergeStrings(configuration.getStringList(pathSetting), "\n")).build()));
        } else {
            throw new ConfigurationException("This message type don't support!");
        }
    }

    public Message getMessage(String path) {
        return messages.get("messages." + path);
    }

    public MessageBuilder getBuilder(String path) {
        return new MessageBuilder(getMessage(path));
    }

}
