package com.example.runebotgradle.tgbot.message;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CommandNotFoundMessage implements TGBotMessage{
    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText("Command not found");

        return message;
    }
}
