package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class DailyRuneMenuMessage implements TGBotMessage {

    @Value("${dailyrunemenu.message}")
    String messageText;

    @Autowired
    TGUserService tgUserService;

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        TGUser user = tgUserService.getTGUser(update);

        message.setText(messageText);

        InlineKeyboardButton enableDailyRuneButton = new InlineKeyboardButton("enable");
        enableDailyRuneButton.setCallbackData(Commands.DATA_ENABLE_DAILY_RUNE.asString());
        InlineKeyboardButton disableDailyRuneButton = new InlineKeyboardButton("disable");
        disableDailyRuneButton.setCallbackData(Commands.DATA_DISABLE_DAILY_RUNE.asString());

        if (user.isDailyRuneEnable()) {
            enableDailyRuneButton.setText("+ " + enableDailyRuneButton.getText());
        }else {
            disableDailyRuneButton.setText("+ " + disableDailyRuneButton.getText());
        }

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(enableDailyRuneButton);
        row1.add(disableDailyRuneButton);

        InlineKeyboardButton toSetUTCMenu = new InlineKeyboardButton("Выбрать часовой пояс ("+ user.getTimeZone().getUtc() +")");
        toSetUTCMenu.setCallbackData(Commands.MENU_UTC_SETUP.asString() + " -3");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(toSetUTCMenu);

        InlineKeyboardButton toMorningTimeMenu = new InlineKeyboardButton("Установить время утра (" + user.getMorningTime()+":00)");
        toMorningTimeMenu.setCallbackData(Commands.MENU_MORNING_TIME_SETUP.asString());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(toMorningTimeMenu);

        InlineKeyboardButton backButton = new InlineKeyboardButton("Назад");
        backButton.setCallbackData(Commands.MENU_MAIN_MENU.asString());

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(backButton);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row1);
        table.add(row2);
        table.add(row3);
        table.add(row4);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
