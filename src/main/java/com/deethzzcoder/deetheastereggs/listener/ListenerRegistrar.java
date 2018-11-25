package com.deethzzcoder.deetheastereggs.listener;

import com.deethzzcoder.deetheastereggs.DeethEasterEggsPlugin;
import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.GeneralEasterEgg;
import com.deethzzcoder.deetheastereggs.easteruser.GeneralEasterUser;
import com.deethzzcoder.deetheastereggs.listener.exception.ListenerException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class ListenerRegistrar {

    public ListenerRegistrar(Plugin plugin, GeneralConfiguration generalConfiguration, GeneralEasterUser generalEasterUser, GeneralEasterEgg generalEasterEgg) {
        if(!(plugin instanceof DeethEasterEggsPlugin)) throw new ListenerException("This JavaPlugin type don't support!");
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerListener(generalConfiguration, generalEasterUser.getEasterUserResolver(), generalEasterUser.getEasterUserFactory(), generalEasterEgg.getEasterEggResolver()), plugin);
    }


}
