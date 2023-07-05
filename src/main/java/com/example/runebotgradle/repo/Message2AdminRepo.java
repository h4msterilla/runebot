package com.example.runebotgradle.repo;

import com.example.runebotgradle.model.Message2Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message2AdminRepo extends CrudRepository<Message2Admin, Long> {
    @Query("select m from Message2Admin m where m.chat2Admin.tgUser.userID = ?1")
    List<Message2Admin> findByChat2Admin_TgUser_UserID(Long userID);

}
