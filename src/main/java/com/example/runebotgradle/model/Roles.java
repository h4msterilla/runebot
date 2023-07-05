package com.example.runebotgradle.model;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String roleName;
    Roles(String roleNAme){
        this.roleName = roleNAme;
    }

    public String getAsString(){
        return roleName;
    }

}
