package com.alankrita.fotos.model;

public class PhotoResponse {
    private PhotoData photos;

    public PhotoResponse(PhotoData photos) {
        this.photos = photos;
    }

    public PhotoData getPhotos() {
        return photos;
    }
}
