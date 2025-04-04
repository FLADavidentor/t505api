package com.example.t505esports.discord;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiscordServiceImpl implements DiscordService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${discord.webhook.url}")
    private String webhookUrl;

    @Override
    public void notificarEvento(String contenido) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of("content", contenido);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);
        restTemplate.postForEntity(webhookUrl, entity, String.class);
    }
}


