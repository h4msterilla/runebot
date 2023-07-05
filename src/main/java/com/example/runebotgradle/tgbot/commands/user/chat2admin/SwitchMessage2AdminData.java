package com.example.runebotgradle.tgbot.commands.user.chat2admin;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.RawMessage;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.chat2admin.Chat2AdminMenuMessage;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@RawMessage(consumes = RawMessageConsumers.MESSAGE_2_ADMIN)
@Component
public class SwitchMessage2AdminData implements TGBotCommand {

    @Override
    public String getName() {
        return Commands.DATA_MESSAGE_2_ADMIN_MODE.asString();
    }

    @Autowired
    TGUserService tgUserService;
    @Autowired
    Chat2AdminMenuMessage chat2AdminMenuMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        String[] command = UpdateParser.getCommand(update).split(" ");

        if (command.length != 2) {
            throw new RuntimeException("wrong data");
        }

        if (command[1].equalsIgnoreCase("--enable")) {
            tgUserService.setWriteMode(update, RawMessageConsumers.MESSAGE_2_ADMIN);
        }
        if (command[1].equalsIgnoreCase("--disable")) {
            tgUserService.setWriteMode(update, RawMessageConsumers.DEFAULT_COMMAND);
        }

        return asList(chat2AdminMenuMessage.get(update), getEmptyCallback(update));
    }
}
