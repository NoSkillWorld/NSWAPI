package fr.noskillworld.api.utils;

public class Credentials {

    private final String user;
    private final String password;
    private final String name;

    public Credentials(String user, String password, String name) {
        this.user = user;
        this.password = password;
        this.name = name;
    }

    public String getDBUser() {
        return user;
    }

    public String getDBPassword() {
        return password;
    }

    public String getDBName() {
        return name;
    }
}
