package com.example.runebotgradle.tgbot.commands;

import com.example.runebotgradle.tgbot.rawmessage.RawMessageConsumers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RawMessage {
    public RawMessageConsumers consumes() default RawMessageConsumers.DEFAULT_COMMAND;
}
