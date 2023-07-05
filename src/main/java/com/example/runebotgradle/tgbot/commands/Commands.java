package com.example.runebotgradle.tgbot.commands;

public enum Commands {
    START_COM("/start"),
    MENU_RUNE_SETUP("/runesetup"),
    MENU_HELP("/help"),
    EXIT_COM("/exit"),
    DATA_GET_RANDOM_RUNE("getRandomRune"),
    DATA_ENABLE_DAILY_RUNE("dailyRune --enable"),
    DATA_DISABLE_DAILY_RUNE("dailyRune --disable "),
    MENU_UTC_SETUP("/setUTC"),//-1 -2 -3 e.g. button table num
    DATA_UTC_SETUP("utc"),//utc UTC-12
    MENU_MORNING_TIME_SETUP("/setMorningTime"),
    MENU_MAIN_MENU("/menu"),
    MENU_ADMIN_PANEL("/adminPanel"),
    COMMAND_NOT_FOUND("/commandNotFound"),
    MENU_ADMIN_STATS("/adminStats"),
    MENU_ADMIN_MESSAGES("/adminMessages"),
    MENU_MESSAGE_2_ADMIN("/message2Admin"),
    DATA_MESSAGE_2_ADMIN_MODE("message2adminMode"),//--enable or --disable
    DATA_RELOAD_MESSAGES_2_ADMIN("reloadMessages2admin");//10

    private String commandName;

    Commands(String commandName){
        this.commandName = commandName;
    }

    public String asString(){
        return commandName;
    }
}
