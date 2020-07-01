package com.alankrita.fotos.model;

import java.util.List;

public class PhotoData {
    private int page;
    private List<Photos> photo;

    public PhotoData(int page, List<Photos> photo) {
        this.page = page;
        this.photo = photo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Photos> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photos> photo) {
        this.photo = photo;
    }
}
