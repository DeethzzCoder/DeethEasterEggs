package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class RenameSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final MainConfiguration mainConfiguration;
    private final EasterEggResolver easterEggResolver;

    RenameSubCommand(GeneralConfiguration generalConfiguration, EasterEggResolver easterEggResolver) {
        super("rename", Arrays.asList("setname"), true, 2, 2);
        this.languageConfiguration = generalConfiguration.getLanguageConfiguration();
        this.mainConfiguration = generalConfiguration.getMainConfiguration();
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("rename-subcommand.doesnt-exist").replaceEggData(args[0]).build().send(sender);
            return;
        }
        if(mainConfiguration.getStringList("blocked-egg-names").contains(args[1])) {
            languageConfiguration.getBuilder("rename-subcommand.blocked-name").replaceEggData(args[1]).build().send(sender);
            return;
        }
        if(easterEggResolver.findEasterEggByName(args[1]) != null) {
            EasterEgg easterEg = easterEggResolver.findEasterEggByName(args[1]);
            languageConfiguration.getBuilder("rename-subcommand.already-exists-name").replaceEggData(easterEg.getName(), easterEg.hasPrize() ? easterEg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEg.getLocation());
            return;
        }
        String oldName = easterEgg.getName();
        easterEgg.setName(args[1]);
        languageConfiguration.getBuilder("rename-subcommand.successfully").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).replaceMessage("%easter-egg-old-name%", oldName).build().send(sender);
    }

}
