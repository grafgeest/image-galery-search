package io.gitlab.agileengine.service;

import io.gitlab.agileengine.service.impl.ImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitializingCache {

    private ImageService imageService;

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent() {
        log.info("Initializing cache");
        imageService.init();
    }
}
