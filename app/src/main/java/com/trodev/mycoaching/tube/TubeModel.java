package com.trodev.mycoaching.tube;

public class TubeModel {

    String title, link, date, key;

    public TubeModel() {
    }

    public TubeModel(String title, String link, String date, String key) {
        this.title = title;
        this.link = link;
        this.date = date;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
