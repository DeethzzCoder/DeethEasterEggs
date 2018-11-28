package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggPurifier;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import com.deethzzcoder.deetheastereggs.easteruser.EasterUserResolver;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class RemoveSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterEggResolver easterEggResolver;
    private final EasterEggPurifier easterEggPurifier;
    private final EasterUserResolver easterUserResolver;

    RemoveSubCommand(LanguageConfiguration languageConfiguration, EasterEggResolver easterEggResolver, EasterEggPurifier easterEggPurifier, EasterUserResolver easterUserResolver) {
        super("remove", Arrays.asList("delete", "del", "rem", "clear", "cl"), true, 1, 1);
        this.languageConfiguration = languageConfiguration;
        this.easterEggResolver = easterEggResolver;
        this.easterEggPurifier = easterEggPurifier;
        this.easterUserResolver = easterUserResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("remove-subcommand.doesnt-exist").replaceEggData(args[0]).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        languageConfiguration.getBuilder("remove-subcommand.successfully").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(sender);
        easterUserResolver.findEasterUsersByEasterEgg(easterEgg).stream().forEach(easterUser -> easterUser.getEasterEggs().remove(easterEgg));
        easterEggPurifier.purifyEasterEggByName(easterEgg.getName());
    }

}
