package com.example.runebotgradle.model;

import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;
import com.example.runebotgradle.tgbot.timezone.MyUTC;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tguser")
public class TGUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userID")
    private Long userID;

    @Column(name = "chatID")
    private Long chatID;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean ACTIVE = true;

    @Column(name = "dailyRune", columnDefinition = "boolean default false")
    private boolean dailyRuneEnable = false;

    @Column(name = "timeZone", columnDefinition = "integer default 12")
    private MyUTC timeZone = MyUTC.p_3;

    @Column(name = "morningTime", columnDefinition = "integer default 8")
    private int morningTime = 8;

    @Column(name = "roleList")
    @OneToMany
    private List<TGRole> roleList;

    //@Column(name = "char2admin")
    @OneToOne
    private Chat2Admin chat2Admin;

    @Column(name = "writeMode", columnDefinition = "integer default 0")
    private RawMessageConsumers writeMode = RawMessageConsumers.DEFAULT_COMMAND;

}
