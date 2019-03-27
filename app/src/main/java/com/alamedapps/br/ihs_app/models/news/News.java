package com.alamedapps.br.ihs_app.models.news;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class News {

    private String title;
    private String content;
    private String image;
    private String registerDate;

    public News() {
    }

    public News(String title, String content, String image, String registerDate) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.registerDate = registerDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
