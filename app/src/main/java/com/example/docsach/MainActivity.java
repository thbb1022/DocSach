package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.GridView;

import com.example.docsach.objects.truyen;
import com.example.docsach.adapters.truyenAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    GridView gdvDanhSachTruyen;
    ArrayList<truyen> truyenArrayList;
    truyenAdapter adapter;
    EditText edtTimKiem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        init();
        anhXa();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.navHome: {
                        fragment = new HomeFragment();
                        break;
                    }
                    case R.id.navFavorite:
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.navProfile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.navAdd:
                        fragment = new AddFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });
    }
    private void init() {
        truyenArrayList = new ArrayList<>();
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW160wTC70dXI0zAnO1g9LL0gpsAz0CA_x1HeEAqckR31jj0A_n_Jg8ygXC_1yI0NT3QxjIyNdz2QTIwCuMBMz/linh-vu-thien-ha.jpg", "Linh Vũ Thiên Hạ", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW180MdfWNqgzzcqzM1w8rLI0oLjK2yMry1HeEAi8jE_1yU9-oqLIUi6QiA_1yI0NT3QxjIyNdz2QTIwDMDBQC/than-dao-dan-ton.jpg", "Thần Đạo Đan Tôn", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDT1zXLSU_3TAp3NDKM1A_zispMMgvwNHbx1HeEgpzUcv3cqpCM4rSkyMQsE_1yI0NT3QxjIyMATtsSpw==/vu-luyen-dien-phong.jpg", "Vũ Luyện Điên Phong", "100"));
        truyenArrayList.add(new truyen("https://static.8cache.com/cover/o/eJzLyTDW13UqDPHxS0sK13UN1A8zNCnPDTRzijT01HeEgoCgSP00b8PKtCKfYscIR2-n_Eo_D7PEqAzzsLCgZJMScyfd3EBn53z9ciNDU90MYyMjAHszGeU=/doc-ton-tam-gioi.jpg", "Độc Tôn Tam Giới", "100"));
        adapter = new truyenAdapter(this, 0, truyenArrayList);
    }

    private void anhXa() {
        gdvDanhSachTruyen = findViewById(R.id.gdvDanhSach);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }

    private void setUp() {
        gdvDanhSachTruyen.setAdapter(adapter);
    }

}
