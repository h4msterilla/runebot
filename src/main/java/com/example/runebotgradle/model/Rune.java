package com.example.runebotgradle.model;

public enum Rune {
    FEHU("ᚠ"),
    URUS("ᚢ"),
    THURISAZ("ᚦ"),
    ANSUZ("ᚨ"),
    RAIDO("ᚱ"),
    KENAZ("ᚲ"),
    GEBO("ᚷ"),
    WUNJO("ᚹ"),
    HAGALAZ("ᚻ"),
    NAUTIZ("ᚾ"),
    ISA("ᛁ"),
    JERA("ᛃ"),
    EYWAS("ᛇ"),
    PERTHE("ᛈ"),
    ALGHIZ("ᛉ"),
    SOWULL("ᛊ"),
    TEIWAZ("ᛏ"),
    BERKANA("ᛒ"),
    EHWAZ("ᛖ"),
    MANNAZ("ᛗ"),
    LAGUS("ᛚ"),
    INGWAZ("ᛝ"),
    OTHALA("ᛟ"),
    DAEGAZ("ᛞ");

    private String symbol;

    Rune(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public static Rune getRandom() {
        return Rune.values()[(int) (Math.random() * Rune.values().length)];
    }
}
