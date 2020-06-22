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
        lstBook.add(new Book("Đấu phá thương khung", "Huyền huyễn", "description", R.drawable.dptk, "2"));
        lstBook.add(new Book("Độc tôn tam giới", "Huyền huyễn", "description", R.drawable.dttg, "3"));
        lstBook.add(new Book("Phàm nhân tu tiên", "Tiên hiệp", "description", R.drawable.pntt, "4"));
        lstBook.add(new Book("Thần khống thiên hạ", "Huyền huyễn", "description", R.drawable.tkth, "5"));
        lstBook.add(new Book("Tối cường thần thoại đế hoàng", "Dị giới", "description", R.drawable.tcttdh, "6"));
        lstBook.add(new Book("Phàm nhân tu tiên 2", "Tiên hiệp", "description", R.drawable.pntt2, "7"));
        lstBook.add(new Book("Nhất niệm vĩnh hằng", "Dị giới", "description", R.drawable.nnvh, "8"));
        lstBook.add(new Book("Đấu la đại lục", "Huyền huyễn", "description", R.drawable.dldl, "9"));
        lstBook.add(new Book("Cầu ma", "Tiên hiệp", "description", R.drawable.cauma, "10"));
        lstBook.add(new Book("Già thiên", "Huyền huyễn", "description", R.drawable.giathien, "11"));
        lstBook.add(new Book("Đế bá", "Huyền huyễn", "description", R.drawable.deba, "12"));

        return lstBook;
    }
}
