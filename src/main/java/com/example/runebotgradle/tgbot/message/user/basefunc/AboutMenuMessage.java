package com.example.runebotgradle.tgbot.message.user.basefunc;

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
public class AboutMenuMessage implements TGBotMessage {

    @Value("${aboutmenu.message}")
    String messageText;

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText(messageText);

        InlineKeyboardButton toMainMenuButton = new InlineKeyboardButton("В главное меню");
        toMainMenuButton.setCallbackData(Commands.MENU_MAIN_MENU.asString());

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(toMainMenuButton);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
