package com.example.docsach;

public class Favorite_item {
    public String uId;
    public String bookId;

    public Favorite_item(){}
    public Favorite_item(String uId, String bookId) {
        this.uId = uId;
        this.bookId = bookId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}