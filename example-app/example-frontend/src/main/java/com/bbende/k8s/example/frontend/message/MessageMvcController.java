package com.bbende.k8s.example.frontend.message;

import com.bbende.k8s.example.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MessageMvcController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageMvcController.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private MessageClientService messageClientService;
    private BuildProperties buildProperties;

    public MessageMvcController(final MessageClientService messageClientService,
                                final BuildProperties buildProperties) {
        this.messageClientService = messageClientService;
        this.buildProperties = buildProperties;
    }

    @GetMapping("/")
    public ModelAndView getAll() {
        try {
            final List<Message> messages = messageClientService.getAll();
            final ModelMap modelMap = new ModelMap("messages", messages);

            final Instant buildInstant = buildProperties.getTime();
            modelMap.addAttribute("buildTimestamp", DATE_TIME_FORMATTER.format(buildInstant));
            return new ModelAndView("index", modelMap);
        } catch (Throwable t) {
            LOGGER.error(t.getMessage(), t);
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
            messageClientService.add(message);
            return new ModelAndView("redirect:/");
        } catch (Throwable t) {
            LOGGER.error(t.getMessage(), t);
            final ModelMap modelMap = new ModelMap("error", t.getMessage());
            return new ModelAndView("error", modelMap);
        }
    }

}
