package com.deethzzcoder.deetheastereggs;

import com.deethzzcoder.deetheastereggs.command.GeneralEasterEggsCommand;
import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.GeneralEasterEgg;
import com.deethzzcoder.deetheastereggs.easteruser.GeneralEasterUser;
import com.deethzzcoder.deetheastereggs.listener.ListenerRegistrar;
import com.deethzzcoder.deetheastereggs.utility.ExceptionHandler;
import com.deethzzcoder.deetheastereggs.utility.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class DeethEasterEggsPlugin extends JavaPlugin {

    private GeneralEasterEgg generalEasterEgg;
    private GeneralEasterUser generalEasterUser;

    @Override
    public void onEnable() {
        try {
            if(!getDataFolder().exists()) getDataFolder().mkdirs();
            GeneralConfiguration generalConfiguration = new GeneralConfiguration(this);
            if(!checkEnabled(generalConfiguration.getMainConfiguration())) return;
            generalEasterEgg = new GeneralEasterEgg(this, generalConfiguration.getMainConfiguration());
            generalEasterUser = new GeneralEasterUser(this, generalConfiguration.getMainConfiguration(), generalEasterEgg.getEasterEggResolver());

            new ListenerRegistrar(this, generalConfiguration, generalEasterUser, generalEasterEgg);
            new GeneralEasterEggsCommand(this, generalConfiguration, generalEasterEgg, generalEasterUser);

            LoggerUtils.welcome();
        } catch(Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }

    private boolean checkEnabled(MainConfiguration mainConfiguration) {
        if(mainConfiguration.getBoolean("enable")) return true;
        LoggerUtils.warn("&cPlugin will disabled, because option 'enable' in config.yml disabled!");
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(this);
        return false;
    }

    @Override
    public void onDisable() {
        try {
            if(generalEasterEgg != null) generalEasterEgg.getEasterEggRepository().save(generalEasterEgg.getEasterEggStorage());
            if(generalEasterUser != null) generalEasterUser.getEasterUserRepository().save(generalEasterUser.getEasterUserStorage());
            LoggerUtils.farewell();
        } catch(Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }

}
