package com.example.runebotgradle.tgbot.message;

import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TGBotMessage {

    EditMessageText get(Update update);

    default void setIDs(EditMessageText messageText, Update update){
        messageText.setChatId(UpdateParser.getChatID(update));
        messageText.setMessageId(UpdateParser.getMessageID(update));
    }

}
