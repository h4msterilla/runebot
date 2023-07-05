package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.BaseCommand;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.MainMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@BaseCommand
public class MainMenuCommand implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.MENU_MAIN_MENU.asString();
    }

    @Autowired
    MainMenuMessage mainMenuMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {

        if(UpdateParser.isMessage(update)){
            return asList(asSendMessage(mainMenuMessage.get(update)));
        }

        return asList(mainMenuMessage.get(update));
    }
}
