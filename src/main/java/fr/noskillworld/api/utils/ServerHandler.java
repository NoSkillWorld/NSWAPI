package fr.noskillworld.api.utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ServerHandler {

    private final ScheduledThreadPoolExecutor executor;

    public ServerHandler() {
        this.executor = new ScheduledThreadPoolExecutor(1);
    }

    public ScheduledThreadPoolExecutor getExecutor() {
        return this.executor;
    }
}
