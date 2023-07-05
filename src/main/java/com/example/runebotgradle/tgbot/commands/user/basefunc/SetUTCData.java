package com.example.runebotgradle.tgbot.commands.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.basefunc.SetUTCMenuMessage;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class SetUTCData implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.DATA_UTC_SETUP.asString();
    }

    @Autowired
    SetUTCMenuMessage setUTCMenuMessage;
    @Autowired
    TGUserService tgUserService;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        String[] command = UpdateParser.getCommand(update).split(" ");

        MyUTC newUTC = MyUTC.valueOf(command[1]);
        MyUTC oldUTC = tgUserService.getUTC(update);

        if(newUTC == oldUTC){
            return asList(getEmptyCallback(update));
        }

        tgUserService.setUTC(update, newUTC);

        update.getCallbackQuery().setData(Commands.MENU_UTC_SETUP.asString() + " -" + command[2]);
        return asList(setUTCMenuMessage.get(update));
    }
}
