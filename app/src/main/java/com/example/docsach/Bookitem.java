package com.example.docsach;

public class Bookitem {
    public String BookAuthor;
    public String BookCategory;
    public String BookContent;
    public String BookDescription;
    public String BookID;
    public String BookName;
    public String BookThumbnail;

        public Bookitem(){}

    public Bookitem(String bookAuthor, String bookCategory, String bookContent, String bookDescription, String bookID, String bookName, String bookThumbnail) {
        BookAuthor = bookAuthor;
        BookCategory = bookCategory;
        BookContent = bookContent;
        BookDescription = bookDescription;
        BookID = bookID;
        BookName = bookName;
        BookThumbnail = bookThumbnail;
    }
}
