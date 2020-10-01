package io.gitlab.agileengine.service.impl;

import io.gitlab.agileengine.model.Image;
import io.gitlab.agileengine.model.ImagePage;
import io.gitlab.agileengine.repository.ImageRepository;
import io.gitlab.agileengine.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO introduce multithreading for fetching
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    @Value("${base.uri}")
    private String baseUri;

    private RestTemplate restTemplate;
    private ImageRepository repository;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setRepository(ImageRepository repository) {
        this.repository = repository;
    }

    public void init() {
        List<String> imageIds = fetchAllImages();
        imageIds.forEach(this::fetchImage);
        log.info("Data are loaded");
    }

    private List<String> fetchAllImages() {
        List<String> imageIds = new ArrayList<>();
        boolean hasMore = true;
        int pageCount = 1;
        while (hasMore) {
            ResponseEntity<ImagePage> responseEntity = restTemplate.getForEntity(String.format(baseUri+"?page=%s", pageCount++), ImagePage.class);
            ImagePage imagePage = responseEntity.getBody();
            hasMore = imagePage.isHasMore();
            imageIds.addAll(imagePage.getPictures().stream().map(Image::getId).collect(Collectors.toList()));
            log.info("Next page {}", pageCount);
        }
        return imageIds;
    }

    private void fetchImage(String id) {
        ResponseEntity<Image> responseEntity = restTemplate.getForEntity(String.format(baseUri + "/%s", id), Image.class);
        Optional<Image> image = Optional.ofNullable(responseEntity.getBody());
        log.info("Fetching image {}", id);
        image.ifPresent(im-> repository.save(im));
    }

}
