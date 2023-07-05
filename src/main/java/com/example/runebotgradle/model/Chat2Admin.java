package com.example.runebotgradle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Chat2Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "tguser")
    @OneToOne
    private TGUser tgUser;

    @Column(name = "hasNewMsgs")
    boolean hasNewMessages = false;

    @Column(name = "lastMessageTime")
    private Long lastMessageTime = null;

    //@Column(name = "msgs2admin")
    @OneToMany
    private List<Message2Admin> message2AdminList;

    public void addNewMessage2Admin(Message2Admin message2Admin){
        message2AdminList.add(message2Admin);
    }

}
