package com.example.docsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class helloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(1500);
                } catch (Exception e) {

                }
                finally
                {
                    Intent activitymoi=new Intent("com.example.taomanhinhchao.MAINACTIVITY");
                    startActivity(activitymoi);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
}
