package io.gitlab.agileengine.service.impl;

import io.gitlab.agileengine.repository.ImageRepository;
import io.gitlab.agileengine.service.AdminService;
import io.gitlab.agileengine.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private ImageRepository imageRepository;
    private ImageService imageService;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public void invalidateCache() {
        imageRepository.truncateImageTable();
        imageService.init();
    }
}
