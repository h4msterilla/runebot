package com.example.runebotgradle.tgbot.message.admin.util;

import com.example.runebotgradle.model.Message2Admin;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Message2Admin_2_SendMessageConverter implements TGBotMessage {

    public SendMessage convert(Update update, Message2Admin message2Admin){

        SendMessage message = new SendMessage();
        setIDs(message, update);

        message.setText(message2Admin.getText());

        return message;
    }

    private void setIDs(SendMessage sendMessage, Update update){
        sendMessage.setChatId(UpdateParser.getChatID(update));
    }

    @Override
    public EditMessageText get(Update update) {
        throw new RuntimeException("don't call this");
        //return null;
    }
}
