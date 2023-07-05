package com.example.runebotgradle.tgbot.message.admin;

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
public class AdminPanelMessage implements TGBotMessage {

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);
        message.setText("admin panel");

        InlineKeyboardButton statsButton = new InlineKeyboardButton("User stats");
        statsButton.setCallbackData(Commands.MENU_ADMIN_STATS.asString());

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(statsButton);

        InlineKeyboardButton newMessagesButton = new InlineKeyboardButton("Messages (new 0)");
        newMessagesButton.setCallbackData(Commands.MENU_ADMIN_MESSAGES.asString());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(newMessagesButton);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row1);
        table.add(row2);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
