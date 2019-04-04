package com.odyssey.global;

public class GlobalSettings {


    private static final GlobalSettings ourInstance = new GlobalSettings();

    public static GlobalSettings getInstance() {
        return ourInstance;
    }

    private GlobalSettings() {
    }


}
