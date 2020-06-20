package com.example.docsach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    List<Book> lstBook;
    RecyclerView recyclerView;
    EditText editText;
    RecyclerViewAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        //
        init();
        //

        editText = (EditText) findViewById(R.id.search_box_id);
        init();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchItem(editable.toString());
                Log.d("search text: ", editable.toString());
            }
        });
    }


    void init(){
        myData m = new myData() ;
        lstBook = m.myList();
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_search_id);
        myAdapter = new RecyclerViewAdapter(this, lstBook);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(myAdapter);
    }
    void searchItem(String s) {
       List<Book> tmp = new ArrayList<>();
       for(Book item: lstBook)
       {
           Log.d("ten truyen: ", item.getTitle().toString());
           if(item.getTitle().toString().toUpperCase().contains(s.toUpperCase())){
               tmp.add(item);
           }
       }
       myAdapter.searchList(tmp);
    }
}