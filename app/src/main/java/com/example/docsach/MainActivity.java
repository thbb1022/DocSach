package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.docsach.R.id.txt_password;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRef.setValue("Hello, World!");
        //Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_book_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
    }

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navHome:

                    getSupportActionBar().show();
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navFavorite:

                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navProfile:
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user == null) {
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().hide();
                        }
                        fragment = new LoginFragment();
                        loadFragment(fragment);
                        return true;

                    }else {  fragment = new ProfileFragment();
                        if (getSupportActionBar() != null) {
                            getSupportActionBar().hide();
                        }
                        loadFragment(fragment);
                        return true;}
                case R.id.navLib:
                    fragment = new LibraryFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_search:
                openSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmet_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void openSearch(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void btn_signupForm (View view) {

        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
