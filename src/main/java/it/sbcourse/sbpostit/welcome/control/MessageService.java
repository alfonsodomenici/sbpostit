package it.sbcourse.sbpostit.welcome.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class MessageService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Value("${app.postit.welcome}")
    String message;

    @PostConstruct
    public void init() {
        log.info("MessageService is ready...");
    }

    public String welcome() {
        return message;
    }
}
