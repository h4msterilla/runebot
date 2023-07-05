package com.example.runebotgradle.tgbot.rawmessage.consumers;

import com.example.runebotgradle.service.Chat2AdminService;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class Message2AdminConsumer implements RawMessageConsumer {
    @Override
    public RawMessageConsumers getType() {
        return RawMessageConsumers.MESSAGE_2_ADMIN;
    }

    @Autowired
    Chat2AdminService chat2AdminService;

    @Override
    public List<BotApiMethod> process(Update update) {

        //System.out.println("get message - " + UpdateParser.getCommand(update)
        //        + " | message id=" + update.getMessage().getMessageId()
        //        + " | message time=" + update.getMessage().getDate());

        chat2AdminService.saveNewMessage2Admin(update);

        return null;
    }
}
