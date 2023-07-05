package com.example.runebotgradle.repo;

import com.example.runebotgradle.model.TGUser;
import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TGUserRepo extends CrudRepository<TGUser, Long> {

    @Query("select t.id from TGUser t where t.userID = ?1")
    Long getIDByUserID(Long userID);

    @Query("select t.writeMode from TGUser t where t.userID = ?1")
    RawMessageConsumers getWriteModeByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("update TGUser t set t.writeMode = ?1 where t.userID = ?2")
    int updateWriteModeByUserID(RawMessageConsumers writeMode, Long userID);

    @Query("select t.morningTime from TGUser t where t.userID = ?1")
    int getMorningTimeByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("update TGUser t set t.morningTime = ?1 where t.userID = ?2")
    void updateMorningTimeByUserID(int morningTime, Long userID);

    @Transactional
    @Modifying
    @Query("update TGUser t set t.ACTIVE = ?1 where t.userID = ?2")
    void updateACTIVEByUserID(boolean ACTIVE, Long userID);

    @Query("select t.dailyRuneEnable from TGUser t where t.userID = ?1")
    boolean getDailyRuneEnableByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("update TGUser t set t.dailyRuneEnable = ?1 where t.userID = ?2")
    void updateDailyRuneEnableByUserID(boolean dailyRuneEnable, Long userID);

    @Query("select t.timeZone from TGUser t where t.userID = ?1")
    MyUTC getTimeZoneByUserID(Long userID);

    @Transactional
    @Modifying
    @Query("update TGUser t set t.timeZone = ?1 where t.userID = ?2")
    void updateTimeZoneByUserID(MyUTC timeZone, Long userID);

    TGUser findByUserID(Long userID);

    boolean existsByUserID(Long userID);

    List<TGUser> findByACTIVEAndDailyRuneEnable(boolean ACTIVE, boolean dailyRuneEnable);
}
