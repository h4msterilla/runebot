package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.model.Rune;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RandomRuneMessage implements TGBotMessage {
    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText(Rune.getRandom().getSymbol());

        return message;
    }
}
