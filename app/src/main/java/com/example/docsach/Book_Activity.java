package com.example.docsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Book_Activity extends AppCompatActivity {
    private TextView tvTitle, tvCategory, tvDescription;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tvTitle = (TextView) findViewById(R.id.txtTitle);
        tvCategory = (TextView) findViewById(R.id.txtCategory);
        tvDescription = (TextView) findViewById(R.id.txtDescription);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        tvTitle.setText(Title);
        tvDescription.setText(Description);
        img.setImageResource(image);
    }
}
