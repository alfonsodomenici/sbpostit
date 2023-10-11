package it.sbcourse.sbpostit;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class BaseEntityListener {

    Logger log = LoggerFactory.getLogger(getClass());

    @PrePersist
    public void setCreated(BaseEntity entity) {
        entity.setCreatoIl(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdated(BaseEntity entity) {
        entity.setAggiornatoIl(LocalDateTime.now());
    }

    @PostPersist
    public void logPersist(BaseEntity entity) {
        log.info("PostPersist entity...");
        log.info(entity.getClass().getSimpleName());
        log.info(entity.toString());
    }

    @PostUpdate
    public void logUpdate(BaseEntity entity) {
        log.info("PostUpdate entity...");
        log.info(entity.getClass().getSimpleName());
        log.info(entity.toString());
    }

    @PostRemove
    public void logDelete(BaseEntity entity) {
        log.info("PostRemove entity...");
        log.info(entity.getClass().getSimpleName());
        log.info(entity.toString());
    }

}
