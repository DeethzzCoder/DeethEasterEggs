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
            languageConfiguration.getBuilder("relocation-subcommand.doesnt-exist").replaceMessage("%easter-egg-name%", args[0]).build().send(sender);
            return;
        }
        Location location = ((Player) sender).getTargetBlock(null, 10).getLocation();
        if(easterEggResolver.findEasterEggByLocation(location) != null) {
            EasterEgg easterEgg = easterEggResolver.findEasterEggByLocation(location);
            languageConfiguration.getBuilder("relocation-subcommand.already-exists-location").replaceMessage("%easter-egg-name%", easterEgg.getName()).replaceMessage("%easter-egg-prize%", easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage()).replaceMessage("%easter-egg-world%", easterEgg.getLocation().getWorld().getName()).replaceMessage("%easter-egg-x%", String.valueOf(easterEgg.getLocation().getX())).replaceMessage("%easter-egg-y%", String.valueOf(easterEgg.getLocation().getY())).replaceMessage("%easter-egg-z%", String.valueOf(easterEgg.getLocation().getZ())).build().send(sender);
            return;
        }
        EasterEgg easterEgg = easterEggResolver.findEasterEggByName(args[0]);
        Location oldLocation = easterEgg.getLocation();
        easterEgg.setLocation(location);
        languageConfiguration.getBuilder("relocation-subcommand.successfully").replaceMessage("%easter-egg-name%", easterEgg.getName()).replaceMessage("%easter-egg-prize%", easterEgg.hasPrize() ? easterEgg.getPrize() : languageConfiguration.getMessage("none").getMessage()).replaceMessage("%easter-egg-old-world%", oldLocation.getWorld().getName()).replaceMessage("%easter-egg-old-x%", String.valueOf(oldLocation.getX())).replaceMessage("%easter-egg-old-y%", String.valueOf(oldLocation.getY())).replaceMessage("%easter-egg-old-z%", String.valueOf(oldLocation.getZ())).replaceMessage("%easter-egg-world%", easterEgg.getLocation().getWorld().getName()).replaceMessage("%easter-egg-x%", String.valueOf(easterEgg.getLocation().getX())).replaceMessage("%easter-egg-y%", String.valueOf(easterEgg.getLocation().getY())).replaceMessage("%easter-egg-z%", String.valueOf(easterEgg.getLocation().getZ())).build().send(sender);
    }

}
