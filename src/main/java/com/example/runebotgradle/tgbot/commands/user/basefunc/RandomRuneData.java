package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.RandomRuneMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class RandomRuneData implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.DATA_GET_RANDOM_RUNE.asString();
    }

    @Autowired
    private RandomRuneMessage randomRuneMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        return asList(asSendMessage(randomRuneMessage.get(update)), getEmptyCallback(update));
    }
}
