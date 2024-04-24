package com.example.party6;

public class NewsItem {
    private String title;
    private String place;
    private String date;

    public NewsItem(String title, String place, String date) {
        this.title = title;
        this.place = place;
        this.date = date;
    }

    // Геттеры и сеттеры для всех полей
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
