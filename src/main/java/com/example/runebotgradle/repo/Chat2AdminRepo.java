package com.example.runebotgradle.repo;

import com.example.runebotgradle.model.Chat2Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Chat2AdminRepo extends CrudRepository<Chat2Admin, Long> {
    @Query("select c from Chat2Admin c where c.tgUser.userID = ?1")
    Chat2Admin findByTgUser_UserID(Long userID);

    @Query("select (count(c) > 0) from Chat2Admin c where c.tgUser.userID = ?1")
    boolean existsByTgUser_UserID(Long userID);
}
