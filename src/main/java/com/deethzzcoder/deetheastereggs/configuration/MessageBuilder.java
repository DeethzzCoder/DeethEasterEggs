package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.utility.ColorUtils;
import com.deethzzcoder.deetheastereggs.utility.TextComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class MessageBuilder {

    private final TextComponent component;

    public MessageBuilder(Message message) {
        this.component = new TextComponentBuilder(message.getMessage()).setClickEvent(message.getClickEvent()).setHoverEvent(message.getHoverEvent()).build();
    }

    public MessageBuilder replaceMessage(String index, Object to) {
        component.setText(component.getText().replaceAll(index, ColorUtils.color(to.toString())));
        return this;
    }

    public MessageBuilder replaceEggData(String eggName, String eggPrize, Location eggLocation) {
        if(eggName != null) replaceMessage("%easter-egg-name%", eggName);
        if(eggPrize != null) replaceMessage("%easter-egg-prize%", eggPrize);
        if(eggLocation != null) replaceMessage("%easter-egg-world%", eggLocation.getWorld().getName()).replaceMessage("%easter-egg-x%", eggLocation.getX()).replaceMessage("%easter-egg-y%", eggLocation.getY()).replaceMessage("%easter-egg-z%", eggLocation.getZ());
        return this;
    }

    public MessageBuilder replaceEggData(String eggName) {
        return replaceEggData(eggName, null, null);
    }

    public MessageBuilder replaceUserData(String name, UUID uuid, String easterEggs) {
        if(name != null) replaceMessage("%easter-user-name%", name);
        if(uuid != null) replaceMessage("%easter-user-uuid%", uuid);
        if(easterEggs != null) replaceMessage("%easter-user-eggs%", easterEggs);
        return this;
    }

    public MessageBuilder replaceUserData(String name) {
        return replaceUserData(name, null, null);
    }

    public Message build() {
        return new Message(component);
    }

}
