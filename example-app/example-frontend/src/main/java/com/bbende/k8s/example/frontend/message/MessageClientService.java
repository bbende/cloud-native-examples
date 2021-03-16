package com.bbende.k8s.example.frontend.message;

import com.bbende.k8s.example.api.Message;
import com.bbende.k8s.example.frontend.ExampleBackendServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageClientService {

    private final String backendMessagesUrl;
    private final RestTemplate restTemplate;

    public MessageClientService(final RestTemplate restTemplate,
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
