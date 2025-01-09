package com.company;

public class UserState {
    private String state;
    private String lastMessage;

    public UserState(String state) {
        this.state = state;
        this.lastMessage = null;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
