package com.example.runebotgradle.tgbot.rawmessage;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.TGRespond;
import com.example.runebotgradle.tgbot.rawmessage.consumers.RawMessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RawMessageProcessor {

    @Autowired
    TGUserService tgUserService;

    Map<RawMessageConsumers, RawMessageConsumer> consumerMap;

    public RawMessageProcessor(List<RawMessageConsumer> rawMessageConsumerList) {
        consumerMap = rawMessageConsumerList.stream().collect(Collectors.toMap(RawMessageConsumer::getType, consumer -> consumer));
    }

    //public List<BotApiMethod> process(Update update){
    public TGRespond process(Update update) {
        RawMessageConsumers consumer = tgUserService.getWriteMode(update);

        if (!consumerMap.containsKey(consumer)) {
            throw new RuntimeException("consumer for \"" + consumer.toString() + "\" not found");
        }

        return new TGRespond(consumerMap.get(consumer), consumerMap.get(consumer).process(update));
    }

}
