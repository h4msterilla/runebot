package com.example.runebotgradle.tgbot.core;

import org.springframework.stereotype.Component;

@Component
public class CommandPostProcessor {

    public void postProcess(TGRespond tgRespond){

        if(tgRespond.getCommandProcessed() == null){
            return;
        }

        tgRespond.getCommandProcessed().postProcess(tgRespond);

    }

}
