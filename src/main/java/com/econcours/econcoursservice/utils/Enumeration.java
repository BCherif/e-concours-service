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

    public enum NOTIFICATION_TYPE {
        Competition("Concours"),
        Result("Résultat"),
        Candidacy("Candidature");

        private final String desc;

        NOTIFICATION_TYPE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum NOTIFICATION_READING {
        Read("Lue"),
        NonRead("Non lue");

        private final String desc;

        NOTIFICATION_READING(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
