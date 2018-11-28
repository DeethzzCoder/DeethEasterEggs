package com.deethzzcoder.deetheastereggs.command;

import com.deethzzcoder.deetheastereggs.configuration.LanguageConfiguration;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEgg;
import com.deethzzcoder.deetheastereggs.easteregg.EasterEggResolver;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class RelocationSubCommand extends AbstractSubCommand {

    private final LanguageConfiguration languageConfiguration;
    private final EasterEggResolver easterEggResolver;

    RelocationSubCommand(LanguageConfiguration languageConfiguration, EasterEggResolver easterEggResolver) {
        super("relocation", Arrays.asList("reloc", "setlocation", "setloc"), false, 1, 1);
        this.languageConfiguration = languageConfiguration;
        this.easterEggResolver = easterEggResolver;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        if(easterEggResolver.findEasterEggByName(args[0]) == null) {
            languageConfiguration.getBuilder("relocation-subcommand.doesnt-exist").replaceEggData(args[0]).build().send(sender);
            return;
        }
        Location location = ((Player) sender).getTargetBlock(null, 10).getLocation();
        if(easterEggResolver.findEasterEggByLocation(location) != null) {
            EasterEgg easterEgg = easterEggResolver.findEasterEggByLocation(location);
            languageConfiguration.getBuilder("relocation-subcommand.already-exists-location").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation());
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        Location oldLocation = easterEgg.getLocation();
        easterEgg.setLocation(location);
        languageConfiguration.getBuilder("relocation-subcommand.successfully").replaceEggData(easterEgg.getName(), easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage(), easterEgg.getLocation()).replaceMessage("%easter-egg-old-world%", oldLocation.getWorld().getName()).replaceMessage("%easter-egg-old-x%", oldLocation.getX()).replaceMessage("%easter-egg-old-y%", oldLocation.getY()).replaceMessage("%easter-egg-old-z%", oldLocation.getZ()).build().send(sender);
    }

}
