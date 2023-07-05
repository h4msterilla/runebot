package com.example.runebotgradle.service;

import com.example.runebotgradle.model.Chat2Admin;
import com.example.runebotgradle.model.Message2Admin;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface Chat2AdminService {

    void createNewChat2AdminIfNotExits(Update update);

    void createNewChat2Admin(Update update);

    boolean hasChat2Admin(Update update);

    Chat2Admin getChat2Admin(Update update);

    void saveNewMessage2Admin(Update update);

    List<Message2Admin> getAllMessage2Admin(Update update);

}
