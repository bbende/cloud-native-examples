package com.bbende.k8s.example.frontend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private Set<Message> messages = new LinkedHashSet<>();

    public List<Message> getAll() {
        return new ArrayList<>(messages);
    }

    public void add(Message message) {
        messages.add(message);
    }

}
