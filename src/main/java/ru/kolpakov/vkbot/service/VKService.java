package ru.kolpakov.vkbot.service;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.kolpakov.vkbot.model.SendMessageRequest;

import java.util.Random;

@Service
public class VKService {

    @Value("${vk.token}")
    private String token;

    private final RestTemplate restTemplate;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(VKService.class);

    public VKService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    public void sendMessage(SendMessageRequest request){

        String url = "https://api.vk.com/method/messages.send?v=5.236&access_token=" + token;

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("user_id", String.valueOf(request.getUserId()));
        params.add("message", request.getMessage());
        params.add("random_id", String.valueOf(new Random().nextInt()));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, new HttpHeaders());


        String response = restTemplate.postForObject(url, entity, String.class);

        logger.info("Ответ от VK API: {}", response);
    }
}
