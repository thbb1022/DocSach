package com.example.docsach;

import java.util.ArrayList;
import java.util.List;

public class myData {
    public myData() {
    }

    public List<Book> myList(){
        List<Book> lstBook = new ArrayList<>();
        lstBook.add(new Book("Linh Vũ Thiên Hạ", "Tiên hiệp", "description", R.drawable.a, "0"));
        lstBook.add(new Book("Tinh Thần Biến", "Tiên hiệp", "description", R.drawable.b, "1"));
        lstBook.add(new Book("Tên truyen", "Dị giới", "description", R.drawable.a, "2"));
        lstBook.add(new Book("Tên truyen", "Kinh dị", "description", R.drawable.a, "3"));
        lstBook.add(new Book("Tên truyen", "Kinh dị", "description", R.drawable.a, "4"));
        lstBook.add(new Book("Tên truyen", "Huyền huyễn", "description", R.drawable.a, "5"));
        lstBook.add(new Book("Tên truyen", "Huyền huyễn", "description", R.drawable.a, "6"));
        lstBook.add(new Book("Tên truyen", "Huyền huyễn", "description", R.drawable.a, "7"));
        lstBook.add(new Book("Tên truyen", "Huyền huyễn", "description", R.drawable.a, "8"));

        return lstBook;
    }
}
