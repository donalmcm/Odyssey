package com.odyssey.global;

public class GlobalSettings {


    private static GlobalSettings ourInstance = new GlobalSettings();

    public static GlobalSettings getInstance() {
        return ourInstance;
    }

    private GlobalSettings() {
    }


}
