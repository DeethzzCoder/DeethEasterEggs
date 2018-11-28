package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class InfoSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterEggResolver easterEggResolver;

    InfoSubCommand(LanguageConfiguration languageConfiguration, EasterEggResolver easterEggResolver) {
        super("info", Arrays.asList("information"), true, 1, 1);
        this.languageConfiguration = languageConfiguration;
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("info-subcommand.doesnt-exist").replaceEggData(args[0]).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        languageConfiguration.getBuilder("info-subcommand.information").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(sender);
    }

}
