package com.bbende.k8s.example.backend.message;

import com.bbende.k8s.example.api.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class MessageService {

    private Set<Message> messages = new LinkedHashSet<>();

    public List<Message> getAll() {
        return new ArrayList<>(messages);
    }

    public Message add(Message message) {
        message.setId(UUID.randomUUID().toString());
        messages.add(message);
        return message;
    }

}