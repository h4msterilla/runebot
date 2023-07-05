package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.BaseCommand;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.AboutMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@BaseCommand
public class StartCommand implements TGBotCommand {

    @Autowired
    TGUserService tgUserService;
    @Autowired
    AboutMenuMessage aboutMenuMessage;

    @Override
    public String getName() {
        return Commands.START_COM.asString();
    }

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        tgUserService.startCommand(update);

        if (UpdateParser.isMessage(update)) {
            return asList(asSendMessage(aboutMenuMessage.get(update)));
        }

        return asList(aboutMenuMessage.get(update));
    }

}
