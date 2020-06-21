package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Book_Activity extends AppCompatActivity {
    private TextView tvTitle, tvTG,tvTL,tvTT,tvMT;
    private ImageView img;
    private ImageButton fav;
    private Button doctruyen;
    String Title;
    Bookitem truyen;

    String stt;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myData = database.getReference();
        fav = (ImageButton) findViewById(R.id.favbutton);
        doctruyen = (Button) findViewById(R.id.btnAddTruyen);
        tvTitle = (TextView) findViewById(R.id.txtTitle);
        tvTG=(TextView) findViewById(R.id.txtTacGia);
        tvTT=(TextView) findViewById(R.id.txtTenTruyen);
        tvTL =(TextView)findViewById(R.id.txtTheLoai);
        tvMT =(TextView) findViewById(R.id.tvMota) ;
        img = (ImageView) findViewById(R.id.bookthumbnail);

        Intent intent = getIntent();
        Title = intent.getExtras().getString("Title");
        int image = intent.getExtras().getInt("Thumbnail");
        stt = intent.getExtras().getString("stt");
        stt = String.valueOf(Integer.parseInt(stt) + 1);
        tvTitle.setText(Title);

        img.setImageResource(image);
        myData.child("Book").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                truyen = dataSnapshot.getValue(Bookitem.class);
                if(truyen.BookID.contains(stt)){
                    tvTG.setText(truyen.BookAuthor);
                    tvTitle.setText(truyen.BookName);
                    tvTL.setText(truyen.BookCategory);
                    tvTT.setText(truyen.BookName);
                    tvMT.setText(truyen.BookDescription);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        doctruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent docTruyen = new Intent(getApplicationContext(), Content.class);
                docTruyen.putExtra("stt", stt);
                docTruyen.putExtra("TenTruyen", Title);
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
