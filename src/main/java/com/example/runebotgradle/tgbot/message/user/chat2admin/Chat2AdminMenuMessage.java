package com.example.runebotgradle.tgbot.message.user.chat2admin;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.message.TGBotMessage;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Chat2AdminMenuMessage implements TGBotMessage {

    @Autowired
    TGUserService tgUserService;

    @Override
    public EditMessageText get(Update update) {
        boolean enable = tgUserService.getWriteMode(update) == RawMessageConsumers.MESSAGE_2_ADMIN;
        EditMessageText message = new EditMessageText();
        setIDs(message, update);

        message.setText("some msg2admin text");

        InlineKeyboardButton enableWrite2AdminMode = new InlineKeyboardButton((enable ? "+ " : "") + "enable write");
        enableWrite2AdminMode.setCallbackData(Commands.DATA_MESSAGE_2_ADMIN_MODE.asString() + " --enable");

        InlineKeyboardButton disableWrite2AdminMode = new InlineKeyboardButton((enable ? "" : "+ ") + "disable write");
        disableWrite2AdminMode.setCallbackData(Commands.DATA_MESSAGE_2_ADMIN_MODE.asString() + " --disable");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(enableWrite2AdminMode);
        row1.add(disableWrite2AdminMode);

        InlineKeyboardButton reloadAllMessagesButton = new InlineKeyboardButton("load all msg");
        reloadAllMessagesButton.setCallbackData(Commands.DATA_RELOAD_MESSAGES_2_ADMIN.asString() + " all");

        InlineKeyboardButton reloadLast10MessagesButton = new InlineKeyboardButton("load 10 msg");
        reloadLast10MessagesButton.setCallbackData(Commands.DATA_RELOAD_MESSAGES_2_ADMIN.asString() + " 10");

        InlineKeyboardButton hideAllMessages = new InlineKeyboardButton("hide msg");
        hideAllMessages.setCallbackData(Commands.DATA_RELOAD_MESSAGES_2_ADMIN.asString() + " hide");

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(reloadAllMessagesButton);
        row2.add(reloadLast10MessagesButton);
        row2.add(hideAllMessages);

        InlineKeyboardButton toMainMenuButton = new InlineKeyboardButton("назад");
        toMainMenuButton.setCallbackData(Commands.MENU_MAIN_MENU.asString());

        List<InlineKeyboardButton> lastRow = new ArrayList<>();
        lastRow.add(toMainMenuButton);

        List<List<InlineKeyboardButton>> table = new ArrayList<>();
        table.add(row1);
        table.add(row2);
        table.add(lastRow);

        message.setReplyMarkup(new InlineKeyboardMarkup(table));

        return message;
    }
}
