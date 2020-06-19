package com.example.docsach;

public class Book {
    private String Title;
    private String Category;
    private String Description;
    private int Thumbnail;
    public String stt;

    public Book(){
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getStt() {
        return stt;
    }

    public Book(String title, String category, String description, int thumbnail, String stt) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        this.stt = stt;
    }

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
