package io.gitlab.agileengine.service;

import io.gitlab.agileengine.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class InitializingCache {

    @Autowired
    private ImageService imageService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() {
        log.info("Initializing cache");
        imageService.init();
    }
}
