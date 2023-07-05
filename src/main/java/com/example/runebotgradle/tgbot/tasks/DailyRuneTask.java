package com.example.runebotgradle.tgbot.tasks;

import com.example.runebotgradle.model.Rune;
import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.service.TGUserService;
import com.example.runebotgradle.tgbot.core.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Configuration
@EnableScheduling
public class DailyRuneTask {
    @Autowired
    Bot bot;
    @Autowired
    TGUserService tgUserService;

    @Scheduled(cron = "0 0 * * * *")
    public void someShit() {
        //System.out.println(LocalTime.now() + " === " + LocalTime.now().plusSeconds(10).getMinute());
        //System.out.println(LocalTime.now(ZoneId.of("UTC-0")));

        int hour = LocalTime.now(ZoneId.of("UTC-0")).plusSeconds(10).getHour();
        //System.out.println(hour);

        List<TGUser> userList = tgUserService.getDailyRuneUserList();

        userList.forEach(u -> {

            if(u.getTimeZone().getTimeDelta() + hour == u.getMorningTime()){
                System.out.println("send");
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(u.getChatID());
                sendMessage.setText(Rune.getRandom().getSymbol());
                bot.executeMethod(sendMessage);
            }
        });

    }

}
