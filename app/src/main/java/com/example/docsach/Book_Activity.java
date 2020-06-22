package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Book_Activity extends AppCompatActivity {
    private TextView tvTitle, tvTG, tvTL, tvTT, tvMT;
    private ImageView img;
    private Button fav;
    private Button doctruyen;
    FirebaseUser user;
    String uID;
    String Title;
    static  int i = 1;
    Bookitem truyen;
    private DatabaseReference mDatabase;
    String stt;
    boolean check = true;
    String bookID;
    DataSnapshot del;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_book_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        setContentView(R.layout.activity_book);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myData = database.getReference();
        fav = (Button) findViewById(R.id.favbutton);
        doctruyen = (Button) findViewById(R.id.btnAddTruyen);
        tvTitle = (TextView) findViewById(R.id.txtTitle);
        tvTG = (TextView) findViewById(R.id.txtTacGia);
        tvTT = (TextView) findViewById(R.id.txtTenTruyen);
        tvTL = (TextView) findViewById(R.id.txtTheLoai);
        tvMT = (TextView) findViewById(R.id.tvMota);
        img = (ImageView) findViewById(R.id.bookthumbnail);

        Intent intent = getIntent();
        Title = intent.getExtras().getString("Title");
        final int image = intent.getExtras().getInt("Thumbnail");
        stt = intent.getExtras().getString("stt");
        Log.d("aaaaaa", stt);
        tvTitle.setText(Title);

        img.setImageResource(image);
        myData.child("Book").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                truyen = dataSnapshot.getValue(Bookitem.class);
                String id = truyen.BookID;
                if (id.equals(stt)) {
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
            public void  onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        fav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

        if (user != null) {
            uID = user.getUid().toString();
            bookID = stt;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference bookRef = rootRef.child("Favorite");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Favorite_item item = ds.getValue(Favorite_item.class);
                        if(uID.equals(item.uId) && bookID.equals(item.bookId)){
                            check = false;
                            del = ds;
                            fav.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                            break;
                        }
                    }

                    if(check != false){
                        fav.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                        fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //write data
                                mDatabase = FirebaseDatabase.getInstance().getReference();
                                Favorite_item fv = new Favorite_item(uID, bookID);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String key = database.getReference("Favorite").push().getKey();//generate fav_id
                                mDatabase.child("Favorite").child(key).setValue(fv);
                                Toast.makeText(Book_Activity.this, "Đã thêm truyện vào danh sách yêu thích!", Toast.LENGTH_LONG).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                    }
                    else{
                        fav.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                del.getRef().removeValue();
                                Toast.makeText(Book_Activity.this, "Đã bỏ thích!", Toast.LENGTH_LONG).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            bookRef.addListenerForSingleValueEvent(eventListener);
        }
        else{
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Book_Activity.this, "Login để sử dụng chắc năng!", Toast.LENGTH_LONG).show();
                }
            });
        }

        if (user != null) {
            uID = user.getUid().toString();
            bookID = stt;
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference recRef = rootRef.child("Recently");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Favorite_item item = ds.getValue(Favorite_item.class);
                        if(uID.equals(item.uId) && bookID.equals(item.bookId)){
                            check = false;
                            break;
                        }
                    }
                    if(check!=false) {
                        doctruyen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent docTruyen = new Intent(getApplicationContext(), Content.class);
                                docTruyen.putExtra("stt", stt);
                                docTruyen.putExtra("TenTruyen", Title);
                                docTruyen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(docTruyen);

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null) {
                                    String uID = user.getUid().toString();
                                    String bookID = stt;
                                    //
                                    //write data
                                    if(i==4)
                                    {
                                        i = 1;
                                    }
                                    mDatabase = FirebaseDatabase.getInstance().getReference();
                                    Favorite_item fv = new Favorite_item(uID, bookID);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    String key = String.valueOf(i);
                                    mDatabase.child("Recently").child(key).setValue(fv);
                                    i++;
                                }
                            }
                        });
                    }
                    else{
                        doctruyen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent docTruyen = new Intent(getApplicationContext(), Content.class);
                                docTruyen.putExtra("stt", stt);
                                docTruyen.putExtra("TenTruyen", Title);
                                docTruyen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(docTruyen);
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            recRef.addValueEventListener(eventListener);
        }
        else{
            doctruyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent docTruyen = new Intent(getApplicationContext(), Content.class);
                    docTruyen.putExtra("stt", stt);
                    docTruyen.putExtra("TenTruyen", Title);
                    docTruyen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(docTruyen);
                }
            });
        }
    }
}
