package it.sbcourse.sbpostit.welcome.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sbcourse.sbpostit.welcome.control.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@RestController
@RequestMapping("/")
public class WelcomeResources {

    Logger log = LoggerFactory.getLogger(getClass());

    MessageService service;

    public WelcomeResources(MessageService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        log.info("WelcomeResources is ready....");
    }

    @PreDestroy
    public void destroy() {
        log.info("WelcomeResources destroyed....");
    }

    @GetMapping
    public String welcome() {
        return "ciao da spring boot";
    }
}
