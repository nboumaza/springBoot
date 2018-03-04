package com.salimatech.zazie.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadStatus {

    private String threadName;
    private String threadState;

    private boolean isNative = false;
    private boolean isSuspended = false;
    private String lockName;
    private String lockOwnerName;


    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setNative(boolean aNative) {
        isNative = aNative;
    }

    public String getThreadState() {
        return threadState;
    }

    public void setThreadState(String threadState) {
        this.threadState = threadState;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public String getLockOwnerName() {
        return lockOwnerName;
    }

    public void setLockOwnerName(String lockOwnerName) {
        this.lockOwnerName = lockOwnerName;
    }


    @Override
    public String toString() {
        return "ThreadStatus{" +
                ", threadName='" + threadName + '\'' +
                ", threadState='" + threadState + '\'' +
                ", lockName='" + lockName + '\'' +
                ", lockOwnerName='" + lockOwnerName + '\'' +
                '}';
    }
}
