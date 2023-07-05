package com.example.runebotgradle.tgbot.core.util;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateParser {

    public static Long getChatID(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        throw new RuntimeException("cant get chatID from update");
    }

    public static Long getUserID(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new RuntimeException("cant get userID from update");
    }

    public static String getRawMessage(Update update){
        return getCommand(update);
    }

    public static String getCommand(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getText();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData();
        }
        throw new RuntimeException("cant get command from update");
    }

    public static Integer getMessageID(Update update){
        if(update.hasMessage()){
            return update.getMessage().getMessageId();
        }
        if(update.hasCallbackQuery()){
            return update.getCallbackQuery().getMessage().getMessageId();
        }
        throw new RuntimeException("cant get messageID from update");
    }

    public static boolean isMessage(Update update){
        return update.hasMessage();
    }

    public static boolean isData(Update update){
        return update.hasCallbackQuery();
    }

    public static Integer getDate(Update update){
        return update.getMessage().getDate();
    }

    //public static Integer getMessageID(Update update){
    //    return update.getMessage().getMessageId();
    //}

}
