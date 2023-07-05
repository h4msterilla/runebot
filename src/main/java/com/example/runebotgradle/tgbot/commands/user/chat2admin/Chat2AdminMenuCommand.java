package com.example.runebotgradle.tgbot.commands.user.chat2admin;

import com.example.runebotgradle.service.Chat2AdminService;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.user.chat2admin.Chat2AdminMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class Chat2AdminMenuCommand implements TGBotCommand {

    @Override
    public String getName() {
        return Commands.MENU_MESSAGE_2_ADMIN.asString();
    }

    @Autowired
    Chat2AdminMenuMessage chat2AdminMenuMessage;
    @Autowired
    Chat2AdminService chat2AdminService;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        chat2AdminService.createNewChat2AdminIfNotExits(update);
        return asList(chat2AdminMenuMessage.get(update));
    }
}
