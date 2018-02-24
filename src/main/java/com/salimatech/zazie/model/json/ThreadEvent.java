package com.salimatech.zazie.model.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadEvent {
    private final Instant time;
    private final String threadName;
    private final String event;


    public ThreadEvent(Instant time, String threadName, String event) {
        this.time = time;
        this.threadName = threadName;
        this.event = event;
    }

    @Override
    public String toString() {
        return "ThreadEvent{" +
                "time=" + time +
                ", threadName='" + threadName + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
