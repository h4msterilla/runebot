package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class MorningTimeMenuMessage implements TGBotMessage {

    @Autowired
    TGUserService tgUserService;

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText("morning time");
        setIDs(message, update);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        int oldTime = tgUserService.getMorningTime(update);

        for (int i = 0; i < 6; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            table.add(row);
            for (int j = 0; j < 4; j++) {
                int time = i*4 + j;
                InlineKeyboardButton button = new InlineKeyboardButton((time==oldTime? "☑\uFE0F ": "")+time + ":00");
                button.setCallbackData(Commands.MENU_MORNING_TIME_SETUP.asString() + " " + time);
                row.add(button);
            }
        }


        InlineKeyboardButton backButton = new InlineKeyboardButton("назад");
        backButton.setCallbackData(Commands.MENU_RUNE_SETUP.asString());

        List<InlineKeyboardButton> lastRow = new ArrayList<>();
        lastRow.add(backButton);


        table.add(lastRow);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
