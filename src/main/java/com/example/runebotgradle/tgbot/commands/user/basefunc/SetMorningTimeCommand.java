package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.MorningTimeMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class SetMorningTimeCommand implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.MENU_MORNING_TIME_SETUP.asString();
    }

    @Autowired
    MorningTimeMenuMessage morningTimeMenuMessage;
    @Autowired
    TGUserService tgUserService;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        String[] command = UpdateParser.getCommand(update).split(" ");
        if(command.length > 1){
            tgUserService.setMorningTime(update, Integer.parseInt(command[1]));
        }
        return asList(morningTimeMenuMessage.get(update));
    }
}
