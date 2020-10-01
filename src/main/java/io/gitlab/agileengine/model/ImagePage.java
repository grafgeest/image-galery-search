package io.gitlab.agileengine.model;

import lombok.Data;

import java.util.List;

@Data
public class ImagePage {
    private List<Image> pictures;
    private int page;
    private int pageCount;
    private boolean hasMore;
}
