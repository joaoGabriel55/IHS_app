package com.alamedapps.br.ihs_app.models.gallery;

public class Gallery {

    private int id;
    private String imageName;
    private String galeryTitle;
    private String linkGalery;

    public Gallery(int id, String imageName, String galeryTitle, String linkGalery) {
        this.id = id;
        this.imageName = imageName;
        this.galeryTitle = galeryTitle;
        this.linkGalery = linkGalery;
    }

    public Gallery() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getGaleryTitle() {
        return galeryTitle;
    }

    public void setGaleryTitle(String galeryTitle) {
        this.galeryTitle = galeryTitle;
    }

    public String getLinkGalery() {
        return linkGalery;
    }

    public void setLinkGalery(String linkGalery) {
        this.linkGalery = linkGalery;
    }
}
