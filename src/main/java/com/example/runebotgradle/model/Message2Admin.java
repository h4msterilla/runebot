package com.example.runebotgradle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message2Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    //@Column(name = "chat2admin")
    @ManyToOne
    private Chat2Admin chat2Admin;

    @Column(name = "msgtime")
    private Integer messageTime;

    @Column(name = "text", length = 4096)
    private String text;

    /* true - user to admin
        false - admin to user
     */
    @Column(name = "toAdmin")
    private boolean toAdmin;

    @Column(name = "inChatID")
    private Integer inChatID;

    @Column(name = "visible", columnDefinition = "boolean default true")
    private boolean visible;
}
