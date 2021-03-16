package com.bbende.k8s.example.frontend.message;

import com.bbende.k8s.example.api.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public ModelAndView getAll() {
        try {
            final List<Message> messages = messageService.getAll();
            final ModelMap modelMap = new ModelMap("messages", messages);
            return new ModelAndView("index", modelMap);
        } catch (Throwable t) {
            final ModelMap modelMap = new ModelMap("error", t.getMessage());
            return new ModelAndView("error", modelMap);
        }
    }

    @GetMapping("/messages/new")
    public ModelAndView newMessage() {
        final ModelMap modelMap = new ModelMap("message", new Message());
        return new ModelAndView("new", modelMap);
    }

    @PostMapping("/messages")
    public ModelAndView createMessage(
            @ModelAttribute(name = "message") Message message) {
        try {
            messageService.add(message);
            return new ModelAndView("redirect:/");
        } catch (Throwable t) {
            final ModelMap modelMap = new ModelMap("error", t.getMessage());
            return new ModelAndView("error", modelMap);
        }
    }
}
