package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.DeethEasterEggsPlugin;
import com.deethzzcoder.deetheastereggs.configuration.exception.ConfigurationException;
import org.bukkit.plugin.Plugin;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class GeneralConfiguration {

    private final MainConfiguration mainConfiguration;
    private final LanguageConfiguration languageConfiguration;

    public GeneralConfiguration(Plugin plugin) {
        if(!(plugin instanceof DeethEasterEggsPlugin)) throw new ConfigurationException("This JavaPlugin type don't support!");
        this.mainConfiguration = new MainConfiguration(plugin);
        this.languageConfiguration = new LanguageConfiguration(plugin);
    }

    public MainConfiguration getMainConfiguration() {
        return mainConfiguration;
    }

    public LanguageConfiguration getLanguageConfiguration() {
        return languageConfiguration;
    }

}
