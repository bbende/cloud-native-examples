package com.bbende.k8s.example.frontend.message;

import com.bbende.k8s.example.api.Message;
import com.bbende.k8s.example.frontend.ExampleBackendServerProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private final String backendMessagesUrl;
    private final RestTemplate restTemplate;
    private final Set<Message> messages = new LinkedHashSet<>();

    public MessageService(final RestTemplate restTemplate,
                          final ExampleBackendServerProperties backendServerProperties) {
        this.restTemplate = restTemplate;
        this.backendMessagesUrl = backendServerProperties.getProtocol()
                + "://" + backendServerProperties.getHost()
                + ":" + backendServerProperties.getPort()
                + backendServerProperties.getMessagesContext();
    }

    public List<Message> getAll() {
        final ResponseEntity<Message[]> response = restTemplate.getForEntity(backendMessagesUrl, Message[].class);
        return new ArrayList<>(Arrays.asList(response.getBody()));
    }

    public void add(final Message message) {
        restTemplate.postForEntity(backendMessagesUrl, message, Message.class);
    }

}
