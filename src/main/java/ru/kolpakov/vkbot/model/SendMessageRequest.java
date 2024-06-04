package ru.kolpakov.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
@Getter
@Setter
public class SendMessageRequest {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("message")
    private String message;
    private int randomId;

    public SendMessageRequest(long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.randomId = new Random().nextInt();

    }
}
