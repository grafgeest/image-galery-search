package io.gitlab.agileengine.service.impl;

import io.gitlab.agileengine.model.Image;
import io.gitlab.agileengine.repository.ImageRepository;
import io.gitlab.agileengine.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> searchImages(String searchTerm) {
        return imageRepository.searchTerm(searchTerm);
    }
}
