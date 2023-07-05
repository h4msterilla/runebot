package com.example.runebotgradle.tgbot.core;

import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.rawmessage.consumers.RawMessageConsumer;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class TGRespond {

    private List<BotApiMethod> respondMethodList;//sorted arrayList
    private Map<BotApiMethod, BotApiMethod> resultMethodMap = new HashMap<>();

    private TGBotCommand commandProcessed;

    private RawMessageConsumer rawMessageConsumerProcessed;

    public TGRespond(TGBotCommand tgBotCommand, List<BotApiMethod> respondMethodList){
        this.commandProcessed = tgBotCommand;
        this.respondMethodList = respondMethodList;
    }

    public TGRespond(RawMessageConsumer rawMessageConsumer, List<BotApiMethod> respondMethodList){
        this.rawMessageConsumerProcessed = rawMessageConsumer;
        this.respondMethodList = respondMethodList;
    }

    public <result extends Serializable> void addResult(BotApiMethod response, result result){
        if(!respondMethodList.contains(response)){
            throw new RuntimeException();
        }
        if(result instanceof BotApiMethod){
            resultMethodMap.put(response, (BotApiMethod) result);
        }
    }

}
