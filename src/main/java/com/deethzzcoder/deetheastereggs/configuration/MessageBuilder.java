package com.deethzzcoder.deetheastereggs.configuration;

import com.deethzzcoder.deetheastereggs.utility.ColorUtils;
import com.deethzzcoder.deetheastereggs.utility.TextComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Copyright © Nikita (DeethzzCoder) Knyazev [vk.com/deethzzcoder/]
 */

public final class MessageBuilder {

    private final TextComponent component;

    public MessageBuilder(Message message) {
        this.component = new TextComponentBuilder(message.getMessage()).setClickEvent(message.getClickEvent()).setHoverEvent(message.getHoverEvent()).build();
    }

    public MessageBuilder replaceMessage(String index, String to) {
        component.setText(component.getText().replaceAll(index, ColorUtils.color(to)));
        return this;
    }

    public Message build() {
        return new Message(component);
    }

}
