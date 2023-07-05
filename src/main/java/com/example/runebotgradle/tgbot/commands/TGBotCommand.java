package com.example.runebotgradle.tgbot.commands;

import com.example.runebotgradle.tgbot.core.TGRespond;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TGBotCommand {

    String getName();

    List<BotApiMethod> prepareRespond(Update update);

    default void postProcess(TGRespond tgRespond){

    }

    /*default List<BotApiMethod> asList(BotApiMethod... methods) {
        return Arrays.stream(methods).toList();
    }

    default List<BotApiMethod> asList(List<BotApiMethod>... lists) {//to save order

        List<BotApiMethod> resultList = new ArrayList<>();

        Arrays.stream(lists).forEach(list -> {
            resultList.addAll(list);
        });

        return resultList;
    }*/

    default <T extends Object> List<BotApiMethod> asList(T... methods) {
        List<BotApiMethod> resultList = new ArrayList<>();

        Arrays.stream(methods).toList().forEach(m ->{
            if(m instanceof BotApiMethod){
                resultList.add((BotApiMethod) m);
            }
            if(m instanceof List){
                resultList.addAll((List<BotApiMethod>)m);
            }
        });

        return resultList;
    }

    default SendMessage asSendMessage(EditMessageText editMessage) {
        SendMessage message = new SendMessage();
        message.setText(editMessage.getText());
        message.setChatId(Long.parseLong(editMessage.getChatId()));
        message.setReplyMarkup(editMessage.getReplyMarkup());
        return message;
    }

    default AnswerCallbackQuery getEmptyCallback(Update update) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(update.getCallbackQuery().getId());
        return answerCallbackQuery;
    }

    default String getFirstWord(String command) {
        return command.split(" ")[0];
    }

}
