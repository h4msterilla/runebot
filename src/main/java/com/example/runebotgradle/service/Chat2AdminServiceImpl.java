package com.example.runebotgradle.service;

import com.example.runebotgradle.model.Chat2Admin;
import com.example.runebotgradle.model.Message2Admin;
import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.repo.Chat2AdminRepo;
import com.example.runebotgradle.repo.Message2AdminRepo;
import com.example.runebotgradle.repo.TGUserRepo;
import com.example.runebotgradle.tgbot.core.util.UpdateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class Chat2AdminServiceImpl implements Chat2AdminService{

    @Autowired
    TGUserRepo tgUserRepo;
    @Autowired
    Chat2AdminRepo chat2AdminRepo;
    @Autowired
    Message2AdminRepo message2AdminRepo;

    @Override
    public void createNewChat2AdminIfNotExits(Update update) {
        if (!chat2AdminRepo.existsByTgUser_UserID(UpdateParser.getUserID(update))){
            createNewChat2Admin(update);
        }
    }

    @Override
    @Transactional
    public void createNewChat2Admin(Update update) {

        Chat2Admin newChat2Admin = new Chat2Admin();

        TGUser tgUser = tgUserRepo.findByUserID(UpdateParser.getUserID(update));

        newChat2Admin.setTgUser(tgUser);
        tgUser.setChat2Admin(newChat2Admin);

        chat2AdminRepo.save(newChat2Admin);
        tgUserRepo.save(tgUser);

    }

    @Override
    public boolean hasChat2Admin(Update update) {

        //Long ID = tgUserRepo.getIDByUserID(UpdateParser.getUserID(update));

        return chat2AdminRepo.existsByTgUser_UserID(UpdateParser.getUserID(update));
    }

    @Override
    public Chat2Admin getChat2Admin(Update update) {
        return chat2AdminRepo.findByTgUser_UserID(UpdateParser.getUserID(update));
    }


    @Override
    @Transactional
    public void saveNewMessage2Admin(Update update) {

        Message2Admin newMessage2Admin = new Message2Admin();

        newMessage2Admin.setText(UpdateParser.getRawMessage(update));
        newMessage2Admin.setToAdmin(true);
        newMessage2Admin.setMessageTime(UpdateParser.getDate(update));
        newMessage2Admin.setInChatID(UpdateParser.getMessageID(update));
        newMessage2Admin.setVisible(true);

        Chat2Admin chat2Admin = chat2AdminRepo.findByTgUser_UserID(UpdateParser.getUserID(update));
        newMessage2Admin.setChat2Admin(chat2Admin);
        chat2Admin.addNewMessage2Admin(newMessage2Admin);

        message2AdminRepo.save(newMessage2Admin);
        chat2AdminRepo.save(chat2Admin);
    }

    @Override
    public List<Message2Admin> getAllMessage2Admin(Update update) {

        return message2AdminRepo.findByChat2Admin_TgUser_UserID(UpdateParser.getUserID(update));
        //message2AdminRepo.findByChat2Admin_TgUser_UserID()
        //return null;
    }



}
