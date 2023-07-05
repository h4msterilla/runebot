package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.BaseCommand;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.ExitMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@BaseCommand
public class ExitCommand implements TGBotCommand {

    @Autowired
    TGUserService tgUserService;

    @Override
    public String getName() {
        return Commands.EXIT_COM.asString();
    }

    @Autowired
    ExitMessage exitMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        tgUserService.exitCommand(update);

        if (UpdateParser.isMessage(update)) {
            return asList(asSendMessage(exitMessage.get(update)));
        }

        return asList(exitMessage.get(update));
    }
}
