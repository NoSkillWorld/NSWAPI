package fr.noskillworld.api;

public class NSWAPI {

    public static NSWAPI api;

    public NSWAPI() {
        api = this;
    }

    public static NSWAPI getAPI() {
        return api;
    }
}