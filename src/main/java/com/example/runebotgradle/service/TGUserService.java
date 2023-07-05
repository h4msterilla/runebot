package com.example.runebotgradle.service;

import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface TGUserService {

    TGUser getTGUser(Update update);

    void startCommand(Update update);

    void updateUser(Update update);

    void exitCommand(Update update);

    boolean getDailyRuneFlag(Update update);

    void setDailyRuneFlag(Update update, boolean flag);

    MyUTC getUTC(Update update);

    void setUTC(Update update, MyUTC utc);

    int getMorningTime(Update update);

    void setMorningTime(Update update, int time);

    List<TGUser> getDailyRuneUserList();

    boolean hasRole(Update update, String role);

    void setRole(Update update, String role);

    void removeRole(Update update, String role);

    RawMessageConsumers getWriteMode(Update update);

    void setWriteMode(Update update, RawMessageConsumers writeMode);

}
