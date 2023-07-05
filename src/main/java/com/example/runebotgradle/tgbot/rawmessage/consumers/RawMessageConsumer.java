package com.example.runebotgradle.tgbot.rawmessage.consumers;

import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface RawMessageConsumer {

    RawMessageConsumers getType();

    List<BotApiMethod> process(Update update);

}
