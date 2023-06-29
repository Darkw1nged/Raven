package me.darkwinged.raven.struts.enums;

import lombok.Getter;

public enum OnlineStatus {

    ONLINE("Online"),
    AWAY("Away"),
    BUSY("Busy"),
    APPEAR_OFFLINE("Appear Offline");

    @Getter
    private final String name;

    OnlineStatus(String name) {
        this.name = name;
    }


}
