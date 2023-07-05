package com.example.runebotgradle.tgbot.core;

import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.commands.BaseCommand;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.RawMessage;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandProcessor {

    private Map<String, TGBotCommand> commandMap;
    private TGBotCommand errorCommand;

    public CommandProcessor(List<TGBotCommand> commandList) {
        commandMap = commandList.stream().collect(Collectors.toMap(TGBotCommand::getName, command -> command));
        errorCommand = commandMap.get(Commands.COMMAND_NOT_FOUND.asString());
    }

    @Autowired
    TGUserService tgUserService;
    @Autowired
    RawMessageProcessor rawMessageProcessor;

    //public List<BotApiMethod> processUpdate(Update update) {
    public TGRespond processUpdate(Update update) {
        TGBotCommand command = commandMap.get(UpdateParser.getCommand(update).split(" ")[0]);

        boolean hasBaseCommand = false;
        if (command != null
                && command.getClass().isAnnotationPresent(BaseCommand.class)) {
            hasBaseCommand = true;
        }

        if (!hasBaseCommand
                && UpdateParser.isMessage(update)//raw message, not data
                && !tgUserService.getWriteMode(update).equals(RawMessageConsumers.DEFAULT_COMMAND)) {
            return rawMessageProcessor.process(update);
        }

        if (command == null) {
            return new TGRespond(errorCommand, errorCommand.prepareRespond(update));
        }

        if (!command.getClass().isAnnotationPresent(RawMessage.class)) {
            tgUserService.setWriteMode(update, RawMessageConsumers.DEFAULT_COMMAND);
        }
        //System.out.println(command.getClass().getAnnotation(RawMessage.class).consumes().toString());
        return new TGRespond(command, command.prepareRespond(update));
    }

}
