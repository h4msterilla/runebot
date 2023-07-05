package com.example.runebotgradle.tgbot.timezone;

import lombok.Getter;

@Getter
public enum MyUTC {

    m_12(-12,"UTC-12",""),
    m_11(-11,"UTC-11",""),
    m_10(-10,"UTC-10",""),
    m_9(-9,"UTC-9",""),
    m_8(-8,"UTC-8",""),
    m_7(-7,"UTC-7",""),
    m_6(-6,"UTC-6",""),
    m_5(-5,"UTC-5",""),
    m_4(-4,"UTC-4",""),
    m_3(-3,"UTC-3",""),
    m_2(-2,"UTC-2",""),
    m_1(-1,"UTC-1",""),
    z_0(0,"UTC+-0",""),
    p_1(1,"UTC+1",""),
    p_2(2,"UTC+2","Калининград"),
    p_3(3,"UTC+3","Москва"),
    p_4(4,"UTC+4","Самара"),
    p_5(5,"UTC+5","Екатеринбург"),
    p_6(6,"UTC+6","Омск"),
    p_7(7,"UTC+7","Новосибирск"),
    p_8(8,"UTC+8","Иркутск"),
    p_9(9,"UTC+9","Якутск"),
    p_10(10,"UTC+10","Владивосток"),
    p_11(11,"UTC+11","Сахалин"),
    p_12(12,"UTC+12","Камчатка");

    int timeDelta;
    String utc;
    String locations;

    MyUTC(int timeDelta, String utc, String locations){
        this.timeDelta = timeDelta;
        this.utc = utc;
        this.locations = locations;
    }

}
