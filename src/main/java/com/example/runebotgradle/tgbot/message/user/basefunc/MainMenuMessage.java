package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
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
@PropertySource(value = "classpath:admin.properties")
public class MainMenuMessage implements TGBotMessage {

    @Value("${mainmenu.message}")
    String messageText;
    @Value("${user.message2admin}")
    boolean enableMessage2Admin;

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText(messageText);

        InlineKeyboardButton getRuneButton = new InlineKeyboardButton("Случайная руна");
        getRuneButton.setCallbackData(Commands.DATA_GET_RANDOM_RUNE.asString());

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(getRuneButton);

        InlineKeyboardButton runeOfDaySetup = new InlineKeyboardButton("Настройки руны дня");
        runeOfDaySetup.setCallbackData(Commands.MENU_RUNE_SETUP.asString());

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(runeOfDaySetup);

        InlineKeyboardButton toAboutMenu = new InlineKeyboardButton("Подробнее о боте");
        toAboutMenu.setCallbackData(Commands.MENU_HELP.asString());

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(toAboutMenu);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row1);
        table.add(row2);
        table.add(row3);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        if(enableMessage2Admin){
            InlineKeyboardButton message2AdminButton = new InlineKeyboardButton("Задать вопрос!");
            message2AdminButton.setCallbackData(Commands.MENU_MESSAGE_2_ADMIN.asString());
            List<InlineKeyboardButton> message2AdminRow = new ArrayList<>();
            message2AdminRow.add(message2AdminButton);
            table.add(message2AdminRow);
        }

        return message;
    }
}
