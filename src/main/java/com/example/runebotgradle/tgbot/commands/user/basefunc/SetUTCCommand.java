package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.SetUTCMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class SetUTCCommand implements TGBotCommand {
    @Override
    public String getName() {
        return getFirstWord(Commands.MENU_UTC_SETUP.asString());
    }

    @Autowired
    SetUTCMenuMessage setUTCMenuMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        return asList(setUTCMenuMessage.get(update));
    }
}
