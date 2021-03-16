package com.bbende.k8s.example.backend.message;

import com.bbende.k8s.example.api.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageRestController {

    private final MessageStore messageStore;

    public MessageRestController(final MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @PostMapping
    public Message createMessage(@RequestBody final Message message) {
        return messageStore.add(message);
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageStore.getAll();
    }

}
