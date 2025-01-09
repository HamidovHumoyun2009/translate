package com.company;

public class User {
    private long Id;
    private String firstName;
    private String username;

    public User(long chatId, String firstName, String username) {
        this.Id = chatId;
        this.firstName = firstName;
        this.username = username;
    }

    public long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
