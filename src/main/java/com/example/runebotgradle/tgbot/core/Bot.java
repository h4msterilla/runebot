package com.example.runebotgradle.tgbot.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@PropertySource(value = "classpath:application.properties")
public class Bot extends TelegramLongPollingBot {

    private String BOT_TOKEN;
    private String BOT_NAME;

    public Bot(TelegramBotsApi telegramBotsApi,
               @Value("${bot.token}") String BOT_TOKEN,
               @Value("${bot.name}") String BOT_NAME) throws TelegramApiException {

        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT_NAME = BOT_NAME;
        //System.out.println(BOT_TOKEN);
        telegramBotsApi.registerBot(this);
    }

    @Autowired
    CommandProcessor commandProcessor;
    @Autowired
    CommandPostProcessor commandPostProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        //System.out.println(UpdateParser.getCommand(update));

        TGRespond tgRespond = commandProcessor.processUpdate(update);
        List<BotApiMethod> methodList = tgRespond.getRespondMethodList();

        if (methodList == null) {
            return;
        }

        methodList.forEach(m -> {
            try {
                tgRespond.addResult(m, execute(m));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });

        commandPostProcessor.postProcess(tgRespond);
    }

    public void executeMethod(BotApiMethod method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
}
