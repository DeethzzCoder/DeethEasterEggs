package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.utility.ColorUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public class Message {

    private final TextComponent component;

    Message(TextComponent component) {
        this.component = component;
        this.setMessage(getMessage());
    }

    public String getMessage() {
        return component.getText();
    }

    public void setMessage(String message) {
        component.setText(ColorUtils.color(message));
    }

    public ClickEvent getClickEvent() {
        return component.getClickEvent();
    }

    public HoverEvent getHoverEvent() {
        return component.getHoverEvent();
    }

    public void send(Player player) {
        player.spigot().sendMessage(component);
    }

    public void send(CommandSender sender) {
        if(sender instanceof Player) {
            send((Player) sender);
        } else {
            sender.sendMessage(getMessage());
        }
    }

}
