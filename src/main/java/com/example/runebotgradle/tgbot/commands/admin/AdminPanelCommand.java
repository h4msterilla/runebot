package com.example.runebotgradle.tgbot.commands.admin;

import com.example.runebotgradle.model.Roles;
import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.commands.Commands;
import com.example.runebotgradle.tgbot.commands.TGBotCommand;
import com.example.runebotgradle.tgbot.message.admin.AdminPanelMessage;
import com.example.runebotgradle.tgbot.message.CommandNotFoundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@PropertySource(value = "classpath:admin.properties")
public class AdminPanelCommand implements TGBotCommand {

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public String getName() {
        return Commands.MENU_ADMIN_PANEL.asString();
    }

    @Autowired
    private AdminPanelMessage adminPanelMessage;
    @Autowired
    private TGUserService tgUserService;
    @Autowired
    private CommandNotFoundMessage commandNotFoundMessage;

    @Override
    public List<BotApiMethod> prepareRespond(Update update) {

        String[] command = UpdateParser.getCommand(update).split(" ");

        if (UpdateParser.isData(update)
                && command.length == 1
                && tgUserService.hasRole(update, Roles.ADMIN.getAsString())) {
            asList(adminPanelMessage.get(update));
        }

        if (command.length == 2 && command[1].equals(adminPassword)) {
            tgUserService.setRole(update, Roles.ADMIN.getAsString());
            return asList(asSendMessage(adminPanelMessage.get(update)));
        }

        return asList(asSendMessage(commandNotFoundMessage.get(update)));
    }
}
