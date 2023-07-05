package com.example.runebotgradle.tgbot.commands.user.chat2admin;

import com.example.runebotgradle.model.Message2Admin;
import com.example.runebotgradle.service.Chat2AdminService;
import com.example.runebotgradle.tgbot.core.TGRespond;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.admin.util.Message2Admin_2_SendMessageConverter;
import com.example.runebotgradle.tgbot.message.user.chat2admin.Chat2AdminMenuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReloadMessages2AdminData implements TGBotCommand {
    @Override
    public String getName() {
        return Commands.DATA_RELOAD_MESSAGES_2_ADMIN.asString();
    }

    @Autowired
    Chat2AdminService chat2AdminService;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {
        String[] command = UpdateParser.getCommand(update).split(" ");

        if (command[1].equalsIgnoreCase("all")) {
            return reloadAllStrategy(update);
        }

        if (command[1].equalsIgnoreCase("hide")) {
            return hideAllStrategy(update);
        }

        return reloadSomeStrategy(update, Integer.getInteger(command[1]));
    }

    @Autowired
    Message2Admin_2_SendMessageConverter converter;
    @Autowired
    Chat2AdminMenuMessage chat2AdminMenuMessage;

    private List<BotApiMethod> reloadAllStrategy(Update update) {
        List<Message2Admin> message2AdminList = chat2AdminService.getAllMessage2Admin(update);
        message2AdminList.sort( (m1, m2)->{
            if(m1.getMessageTime() > m2.getMessageTime()) return 1;
            else return -1;
        });

        List<BotApiMethod> sendMessageList = new ArrayList<>();

        message2AdminList.forEach(m -> {
            sendMessageList.add(converter.convert(update, m));
        });

        return asList(//chat2AdminMenuMessage.get(update),
                sendMessageList,
                getEmptyCallback(update));
    }

    private List<BotApiMethod> hideAllStrategy(Update update) {
        return asList(getEmptyCallback(update));
    }

    private List<BotApiMethod> reloadSomeStrategy(Update update, int count) {
        return asList(getEmptyCallback(update));
    }

    @Override
    public void postProcess(TGRespond tgRespond) {
        //System.out.println("hello");
        
    }
}
