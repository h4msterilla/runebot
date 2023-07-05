package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
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
public class EnableDailyRuneCommand implements TGBotCommand {
    @Override
    public String getName() {
        return getFirstWord(Commands.DATA_ENABLE_DAILY_RUNE.asString());
    }

    @Autowired
    DailyRuneMenuMessage dailyRuneMenuMessage;
    @Autowired
    TGUserService tgUserService;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        if(UpdateParser.getCommand(update).equalsIgnoreCase(Commands.DATA_ENABLE_DAILY_RUNE.asString())){
            tgUserService.setDailyRuneFlag(update, true);
        }else {
            tgUserService.setDailyRuneFlag(update, false);
        }
        return asList(dailyRuneMenuMessage.get(update));
    }
}
