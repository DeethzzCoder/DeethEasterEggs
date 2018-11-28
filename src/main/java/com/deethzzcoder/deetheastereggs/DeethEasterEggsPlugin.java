package com.deethzzcoder.deetheastereggs;

import com.deethzzcoder.deetheastereggs.command.GeneralEasterEggsCommand;
import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.GeneralEasterEgg;
import com.deethzzcoder.deetheastereggs.easteruser.GeneralEasterUser;
import com.deethzzcoder.deetheastereggs.listener.ListenerRegistrar;
import com.deethzzcoder.deetheastereggs.utility.ColorUtils;
import com.deethzzcoder.deetheastereggs.utility.ExceptionHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class DeethEasterEggsPlugin extends JavaPlugin {

    private GeneralEasterEgg generalEasterEgg;
    private GeneralEasterUser generalEasterUser;

    private static final String WELCOME_MESSAGE = "\n" +
            "&a<<<===----------------------------------------------------------------------===>>>\n" +
            "   &aThe plugin DeethEasterEggs was successfully enabled!\n" +
            "   &aDeveloper of the plugin: Nikita (DeethzzCoder) Knyazev [www.spigotmc.org/members/deethzzcoder.611082/]\n" +
            "   &aAll found bugs, you can send to developer!\n" +
            "   &aThanks for using this plugin!\n" +
            "&a<<<===----------------------------------------------------------------------===>>>";

    private static final String FAREWELL_MESSAGE = "\n" +
            "&a<<<===----------------------------------------------------------------------===>>>\n" +
            "   &aThe plugin DeethEasterEggs was successfully disabled!\n" +
            "   &aDeveloper of the plugin: Nikita (DeethzzCoder) Knyazev [www.spigotmc.org/members/deethzzcoder.611082/]\n" +
            "   &aAll found bugs, you can send to developer!\n" +
            "   &aThanks for using this plugin!\n" +
            "&a<<<===----------------------------------------------------------------------===>>>";

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

            getLogger().info(ColorUtils.color(WELCOME_MESSAGE));
        } catch(Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }

    private boolean checkEnabled(MainConfiguration mainConfiguration) {
        if(mainConfiguration.getBoolean("enable")) return true;
        getLogger().warning(ColorUtils.color("&cPlugin will disabled, because option 'enable' in config.yml disabled!"));
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.disablePlugin(this);
        return false;
    }

    @Override
    public void onDisable() {
        try {
            if(generalEasterEgg != null) generalEasterEgg.getEasterEggRepository().save(generalEasterEgg.getEasterEggStorage());
            if(generalEasterUser != null) generalEasterUser.getEasterUserRepository().save(generalEasterUser.getEasterUserStorage());
            getLogger().info(ColorUtils.color(FAREWELL_MESSAGE));
        } catch(Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }

}
