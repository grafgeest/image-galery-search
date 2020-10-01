package io.gitlab.agileengine.service;

import io.gitlab.agileengine.model.Image;
import io.gitlab.agileengine.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SearchService {

    List<Image> searchImages(String searchTerm);

}
