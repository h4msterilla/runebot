package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.DailyRuneMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class RuneSetupCommand implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.MENU_RUNE_SETUP.asString();
    }

    @Autowired
    DailyRuneMenuMessage dailyRuneMenuMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {

        if(UpdateParser.isMessage(update)){
            return asList(asSendMessage(dailyRuneMenuMessage.get(update)));
        }

        return asList(dailyRuneMenuMessage.get(update));
    }
}
