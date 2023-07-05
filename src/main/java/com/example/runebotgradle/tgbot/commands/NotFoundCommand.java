package com.example.runebotgradle.tgbot.commands;

import com.example.runebotgradle.tgbot.message.CommandNotFoundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class NotFoundCommand implements TGBotCommand{
    @Override
    public String getName() {
        return Commands.COMMAND_NOT_FOUND.asString();
    }

    @Autowired
    private CommandNotFoundMessage commandNotFoundMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        return asList(asSendMessage(commandNotFoundMessage.get(update)));
    }
}
