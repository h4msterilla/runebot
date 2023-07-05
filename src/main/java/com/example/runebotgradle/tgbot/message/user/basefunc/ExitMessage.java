package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExitMessage implements TGBotMessage {
    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText("exit text");

        InlineKeyboardButton startAgain = new InlineKeyboardButton("/start");
        startAgain.setCallbackData(Commands.START_COM.asString());

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(startAgain);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
