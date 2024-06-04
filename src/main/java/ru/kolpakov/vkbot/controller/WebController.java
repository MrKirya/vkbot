package ru.kolpakov.vkbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolpakov.vkbot.model.Message;
import ru.kolpakov.vkbot.model.SendMessageRequest;
import ru.kolpakov.vkbot.service.VKService;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WebController {

    @Value("${vk.confirmation}")
    private String confirmationCode;

    @Value("${vk.secret}")
    private String secretKey;

    private final VKService vkService;


    @PostMapping
    public String handleUpdate(@RequestBody Map<String, Object> request) {
        String type = (String) request.get("type");
        String requestSecret = (String) request.get("secret");
        int groupId = (Integer) request.get("group_id");

        if (!secretKey.equals(requestSecret)) {
            return "fail";
        }

        if ("confirmation".equals(type)) {
            return confirmationCode;
        } else if ("message_new".equals(type)) {
            Map<String, Object> object = (Map<String, Object>) request.get("object");
            Map<String, Object> message = (Map<String, Object>) object.get("message");
            int userId = (Integer) message.get("from_id");
            String body = (String) message.get("text");
            String responseMessage = "Вы сказали: " + body;
            SendMessageRequest response = new SendMessageRequest(userId, responseMessage);
            vkService.sendMessage(response);
        }

        return "ok";
    }
}
