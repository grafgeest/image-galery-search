package io.gitlab.agileengine.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Image {
    @Id
    private String id;
    @Column
    private String author;
    @Column
    private String camera;
    @Column
    private String tags;
    @Column
    private String cropped_picture;
    @Column
    private String full_picture;
}
