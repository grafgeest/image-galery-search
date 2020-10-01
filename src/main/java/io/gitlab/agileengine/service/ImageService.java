package io.gitlab.agileengine.service;

import io.gitlab.agileengine.model.Image;
import io.gitlab.agileengine.model.ImagePage;
import io.gitlab.agileengine.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageService {
    private String uri = "http://interview.agileengine.com:80/images?page=%s";
    private String imageUri = "http://interview.agileengine.com:80/images/%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImageRepository repository;

    public void init() {
        List<String> imageIds = fetchAllImages();
        imageIds.forEach(this::fetchImage);
    }

    private List<String> fetchAllImages() {
        List<String> imageIds = new ArrayList<>();
        boolean hasMore = true;
        int pageCount = 1;
        while (hasMore) {
            ResponseEntity<ImagePage> responseEntity = restTemplate.getForEntity(String.format(uri, pageCount++), ImagePage.class);
            ImagePage imagePage = responseEntity.getBody();
            hasMore = imagePage.isHasMore();
            imageIds.addAll(imagePage.getPictures().stream().map(Image::getId).collect(Collectors.toList()));
            log.info("Next page {}", pageCount);
        }
        return imageIds;
    }

    private void fetchImage(String id) {
        ResponseEntity<Image> responseEntity = restTemplate.getForEntity(String.format(imageUri, id), Image.class);
        repository.save(responseEntity.getBody());
    }


    private List<String> fetchFirstImage() {
        ImagePage imagePage = restTemplate.getForEntity(String.format(uri, 1), ImagePage.class).getBody();
        return imagePage.getPictures().stream().map(Image::getId).collect(Collectors.toList());

    }

}
