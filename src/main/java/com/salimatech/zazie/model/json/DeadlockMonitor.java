package com.salimatech.zazie.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeadlockMonitor {

    private boolean deadlockFound;
    private List<ThreadStatus> deadlockInfo ;


    public DeadlockMonitor(List<ThreadStatus> threadStates) {
        this.deadlockInfo = threadStates;
        this.deadlockFound = false;
    }

    public boolean isDeadlockFound() {
        return deadlockFound;
    }

    public void setDeadlockFound(boolean deadlockFound) {
        this.deadlockFound = deadlockFound;
    }

    public List<ThreadStatus> getDeadlockInfo() {
        return deadlockInfo;
    }

    public void setDeadlockInfo(List<ThreadStatus> deadlockInfo) {
        this.deadlockInfo = deadlockInfo;
    }


}
