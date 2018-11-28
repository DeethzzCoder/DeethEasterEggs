package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.GeneralConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.configuration.MainConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggFactory;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import com.deethzzcoder.deetheastereggs.utility.StringUtils;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class CreateSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final MainConfiguration mainConfiguration;
    private final EasterEggResolver easterEggResolver;
    private final EasterEggFactory easterEggFactory;

    CreateSubCommand(GeneralConfiguration generalConfiguration, EasterEggResolver easterEggResolver, EasterEggFactory easterEggFactory) {
        super("create", Arrays.asList("set", "add"), false, 1, -1);
        this.languageConfiguration = generalConfiguration.getLanguageConfiguration();
        this.mainConfiguration = generalConfiguration.getMainConfiguration();
        this.easterEggResolver = easterEggResolver;
        this.easterEggFactory = easterEggFactory;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(mainConfiguration.getStringList("blocked-egg-names").contains(args[0])) {
            languageConfiguration.getBuilder("create-subcommand.blocked-name").replaceEggData(args[0]).build().send(sender);
            return;
        }
        if(easterEggResolver.findEasterEggByName(args[0]) != null) {
            EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
            languageConfiguration.getBuilder("create-subcommand.already-exists-name").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(sender);
            return;
        }
        Location location = ((Player) sender).getTargetBlock(null, 10).getLocation();
        if(easterEggResolver.findEasterEggByLocation(location) != null) {
            EasterEgg easterEgg = easterEggResolver.findEasterEggByLocation(location);
            languageConfiguration.getBuilder("create-subcommand.already-exists-location").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(sender);
            return;
        }
        if(args.length == 1) {
            EasterEgg easterEgg = easterEggFactory.makeEasterEgg(args[0], location);
            languageConfiguration.getBuilder("create-subcommand.successfully").replaceEggData(easterEgg.getName(), languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggFactory.makeEasterEgg(args[0], StringUtils.merge(ArrayUtils.remove(args, 0), " "), location);
        languageConfiguration.getBuilder("create-subcommand.successfully").replaceEggData(easterEgg.getName(), easterEgg.getPrize(), easterEgg.getLocation()).build().send(sender);
    }

}
