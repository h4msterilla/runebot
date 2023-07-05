package com.example.runebotgradle.tgbot.message.user.basefunc;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetUTCMenuMessage implements TGBotMessage {

    @Override
    public EditMessageText get(Update update) {
        EditMessageText message = new EditMessageText();
        message.setText("UTC setup");
        setIDs(message, update);

        InlineKeyboardButton backButton = new InlineKeyboardButton("назад");
        backButton.setCallbackData(Commands.MENU_RUNE_SETUP.asString());

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(backButton);

        List<List<InlineKeyboardButton>> table = getUTCButtonTable(update);
        table.add(row);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }

    private static final int buttonsOnBoard = 6;

    @Autowired
    TGUserService tgUserService;

    private List<List<InlineKeyboardButton>> getUTCButtonTable(Update update) {

        String[] command = UpdateParser.getCommand(update).split(" ");
        if (command.length <= 1) {
            throw new RuntimeException("wrong command");
        }

        int buttonBlock = Integer.parseInt(command[1]) * -1;

        List<List<InlineKeyboardButton>> table = new ArrayList<>();

        MyUTC userUTC = tgUserService.getUTC(update);

        for (int i = (buttonBlock - 1) * buttonsOnBoard; i < buttonBlock * buttonsOnBoard; i++) {
            if (i < MyUTC.values().length) {
                MyUTC utc = MyUTC.values()[i];

                InlineKeyboardButton button = new InlineKeyboardButton((utc==userUTC?"☑\uFE0F ":"") + utc.getUtc() + " " + utc.getLocations());
                button.setCallbackData(Commands.DATA_UTC_SETUP.asString() + " " + utc.toString()+ " " + buttonBlock);

                List<InlineKeyboardButton> row = new ArrayList<>();
                row.add(button);
                table.add(row);
            }
        }

        int maxButtonTables = MyUTC.values().length / buttonsOnBoard;
        if (MyUTC.values().length % buttonsOnBoard > 0) {
            maxButtonTables++;
        }
        //System.out.println(maxButtonTables);
        InlineKeyboardButton toLeft = new InlineKeyboardButton("<-");
        toLeft.setCallbackData(Commands.MENU_UTC_SETUP.asString() + " -" + (buttonBlock <= 1 ? maxButtonTables : buttonBlock - 1));

        InlineKeyboardButton toRight = new InlineKeyboardButton("->");
        toRight.setCallbackData(Commands.MENU_UTC_SETUP.asString() + " -" + (buttonBlock >= maxButtonTables ? 1 : buttonBlock + 1));

        List<InlineKeyboardButton> lastRow = new ArrayList<>();
        lastRow.add(toLeft);
        lastRow.add(toRight);

        table.add(lastRow);

        return table;
    }
}
