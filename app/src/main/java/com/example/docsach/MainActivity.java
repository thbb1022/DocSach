package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.GridView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Book> lstBook;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navFavorite:
                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navProfile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navAdd:
                    fragment = new AddFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmet_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void init(){
        lstBook = new ArrayList<>();
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        lstBook.add(new Book("Tên truyen", "category", "description", R.drawable.a));
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recycleview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }
}
