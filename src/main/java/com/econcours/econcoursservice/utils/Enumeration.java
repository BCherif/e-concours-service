package com.econcours.econcoursservice.utils;

public class Enumeration {
    public enum STATE_CANDIDACY {
        Accept("Accepter"),
        Refuse("Refuser"),
        Instance("Instance");

        private final String desc;

        STATE_CANDIDACY(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
