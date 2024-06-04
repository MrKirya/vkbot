package ru.kolpakov.vkbot.model;

public class Message {

    private int id;
    private int userId;

    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message(){}

    public Message(int id, int userId, String body) {
        this.id = id;
        this.userId = userId;
        this.body = body;
    }
}
