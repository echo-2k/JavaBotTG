package com.example.bot;

public class UserData {
    private Long telegramId;
    private String username;

    // Constructors, getters, setters
    public UserData() {}

    public UserData(Long telegramId, String username) {
        this.telegramId = telegramId;
        this.username = username;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
