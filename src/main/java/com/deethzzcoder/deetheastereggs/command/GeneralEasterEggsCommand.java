package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.DeethEasterEggsPlugin;
import com.deethzzcoder.deetheastereggs.command.exception.CommandException;
import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.GeneralEasterEgg;
import com.deethzzcoder.deetheastereggs.easteruser.GeneralEasterUser;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class GeneralEasterEggsCommand {

    private final Set<AbstractSubCommand> subCommands = new HashSet<>();

    public GeneralEasterEggsCommand(JavaPlugin plugin, GeneralConfiguration generalConfiguration, GeneralEasterEgg generalEasterEgg, GeneralEasterUser generalEasterUser) {
        if(!(plugin instanceof DeethEasterEggsPlugin)) throw new CommandException("This JavaPlugin type don't support!");
        plugin.getCommand("eastereggs").setExecutor(new EasterEggsCommand(generalConfiguration.getLanguageConfiguration(), this));
        subCommands.add(new ReloadSubCommand(generalConfiguration.getLanguageConfiguration(), plugin));
        subCommands.add(new CreateSubCommand(generalConfiguration, generalEasterEgg.getEasterEggResolver(), generalEasterEgg.getEasterEggFactory()));
        subCommands.add(new RemoveSubCommand(generalConfiguration.getLanguageConfiguration(), generalEasterEgg.getEasterEggResolver(), generalEasterEgg.getEasterEggPurifier(), generalEasterUser.getEasterUserResolver()));
        subCommands.add(new RenameSubCommand(generalConfiguration, generalEasterEgg.getEasterEggResolver()));
        subCommands.add(new RelocationSubCommand(generalConfiguration.getLanguageConfiguration(), generalEasterEgg.getEasterEggResolver()));
        subCommands.add(new ReprizeSubCommand(generalConfiguration, generalEasterEgg.getEasterEggResolver()));
        subCommands.add(new InfoSubCommand(generalConfiguration.getLanguageConfiguration(), generalEasterEgg.getEasterEggResolver()));
        subCommands.add(new PlayerInfoSubCommand(generalConfiguration.getLanguageConfiguration(), generalEasterUser.getEasterUserResolver()));
    }

    public AbstractSubCommand findSubCommand(String name) {
        for(AbstractSubCommand subCommand : subCommands) {
            if(subCommand.getName().equals(name) || subCommand.getAliases().contains(name)) return subCommand;
        }
        return null;
    }

}
