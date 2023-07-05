package com.example.runebotgradle.service;

import com.example.runebotgradle.model.TGRole;
import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.repo.TGRoleRepo;
import com.example.runebotgradle.repo.TGUserRepo;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TGUserServiceImpl implements TGUserService {

    @Autowired
    TGUserRepo tgUserRepo;

    @Override
    public TGUser getTGUser(Update update) {
        return tgUserRepo.findByUserID(UpdateParser.getUserID(update));
    }

    @Override
    public void startCommand(Update update) {
        Long userID = UpdateParser.getUserID(update);

        if (tgUserRepo.existsByUserID(userID)) {
            TGUser user = tgUserRepo.findByUserID(userID);
            user.setACTIVE(true);
            tgUserRepo.save(user);
            return;
        }

        TGUser newUser = new TGUser();
        newUser.setUserID(userID);
        newUser.setChatID(UpdateParser.getChatID(update));
        newUser.setACTIVE(true);
        newUser.setDailyRuneEnable(false);
        newUser.setTimeZone(MyUTC.p_3);

        tgUserRepo.save(newUser);
    }

    @Override
    public void updateUser(Update update) {

    }


    @Override
    public void exitCommand(Update update) {
        tgUserRepo.updateACTIVEByUserID(false, UpdateParser.getUserID(update));
    }

    @Override
    public boolean getDailyRuneFlag(Update update) {
        return tgUserRepo.getDailyRuneEnableByUserID(UpdateParser.getUserID(update));
    }

    @Override
    public void setDailyRuneFlag(Update update, boolean flag) {
        tgUserRepo.updateDailyRuneEnableByUserID(flag, UpdateParser.getUserID(update));
    }

    @Override
    public MyUTC getUTC(Update update) {
        return tgUserRepo.getTimeZoneByUserID(UpdateParser.getUserID(update));
    }

    @Override
    public void setUTC(Update update, MyUTC utc) {
        tgUserRepo.updateTimeZoneByUserID(utc, UpdateParser.getUserID(update));
    }

    @Override
    public int getMorningTime(Update update) {
        return tgUserRepo.getMorningTimeByUserID(UpdateParser.getUserID(update));
    }

    @Override
    public void setMorningTime(Update update, int time) {
        tgUserRepo.updateMorningTimeByUserID(time, UpdateParser.getUserID(update));
    }

    @Override
    public List<TGUser> getDailyRuneUserList() {
        return tgUserRepo.findByACTIVEAndDailyRuneEnable(true, true);
    }

    @Autowired
    TGRoleRepo tgRoleRepo;

    @Override
    public boolean hasRole(Update update, String role) {//not tested

        List<TGRole> roleList = tgRoleRepo.findByUser_UserID(UpdateParser.getUserID(update));

        AtomicBoolean hasRole = new AtomicBoolean(false);

        roleList.forEach(r -> {
            if (r.getRole().equalsIgnoreCase(role)) {
                hasRole.set(true);
            }
        });

        return hasRole.get();
    }

    @Override
    public void setRole(Update update, String role) {//not tested
        List<TGRole> roleList = tgRoleRepo.findByUser_UserID(UpdateParser.getUserID(update));

        AtomicBoolean hasRole = new AtomicBoolean(false);

        roleList.forEach(r -> {
            if (r.getRole().equalsIgnoreCase(role)) {
                hasRole.set(true);
            }
        });

        if (hasRole.get()) {
            return;
        }

        TGRole newRole = new TGRole();
        newRole.setRole(role);

        TGUser user = tgUserRepo.findByUserID(UpdateParser.getUserID(update));

        newRole.setUser(user);
        tgRoleRepo.save(newRole);

        user.getRoleList().add(newRole);
        tgUserRepo.save(user);

    }

    @Override
    public void removeRole(Update update, String role) {//not tested
        List<TGRole> roleList = tgRoleRepo.findByUser_UserID(UpdateParser.getUserID(update));

        AtomicReference<TGRole> delRole = null;

        roleList.forEach(r ->{
            if(r.getRole().equalsIgnoreCase(role)){
                delRole.set(r);
            }
        });

        if(delRole.get() == null){
            return;
        }

        TGUser user = tgUserRepo.findByUserID(UpdateParser.getUserID(update));
        user.getRoleList().remove(delRole);

        tgUserRepo.save(user);
        tgRoleRepo.delete(delRole.get());
    }

    @Override
    public RawMessageConsumers getWriteMode(Update update) {
        return tgUserRepo.getWriteModeByUserID(UpdateParser.getUserID(update));
    }

    @Override
    public void setWriteMode(Update update, RawMessageConsumers writeMode) {
        tgUserRepo.updateWriteModeByUserID(writeMode, UpdateParser.getUserID(update));
    }
}
