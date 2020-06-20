package com.example.docsach;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Book_Activity extends AppCompatActivity {
    private TextView tvTitle, tvCategory;
    private ImageView img;
    private ImageButton fav;
    private Button addtruyen;
    String stt;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_book_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_book);
        fav = (ImageButton) findViewById(R.id.favbutton);
        addtruyen = (Button) findViewById(R.id.btnAddTruyen);
        tvTitle = (TextView) findViewById(R.id.txtTitle);
        tvCategory = (TextView) findViewById(R.id.txtCategory);
//        tvDescription = (TextView) findViewById(R.id.txtDescription);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Category = intent.getExtras().getString("Category");
//        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");
        stt = intent.getExtras().getString("stt");
        tvTitle.setText(Title);
        tvCategory.setText(Category);
//        tvDescription.setText(Description);
        img.setImageResource(image);
        addtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent docTruyen = new Intent(getApplicationContext(), Content.class);
                docTruyen.putExtra("stt", stt);
                getApplicationContext().startActivity(docTruyen);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Book_Activity.this,"Đã thêm truyện vào danh sách yêu thích!",Toast.LENGTH_LONG).show();

            }
        });
    }
}
