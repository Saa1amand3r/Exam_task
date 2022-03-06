package com.example.Downloader.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Images {

    //    variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Blob image;
    String uri;
    String image_type;
    Long image_size;
    String image_name;

//    constructors
    public Images() {
    }

    public Images(Blob image) {
        this.image = image;
    }


// getters and setters
    public String getURI() {
        return uri;
    }
    public void setURI(String uri) {
        this.uri = uri;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public Long getImage_size() {
        return image_size;
    }

    public void setImage_size(Long image_size) {
        this.image_size = image_size;
    }
}
