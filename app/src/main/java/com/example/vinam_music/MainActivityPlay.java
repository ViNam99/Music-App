package com.example.vinam_music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinam_music.MyService.MyBinder;

public class MainActivityPlay extends AppCompatActivity {

    private MyService myService;
    private boolean isBound = false;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_play);

        final ImageView imgPLay = findViewById(R.id.imgPlay);
        final ImageView imgNext = findViewById(R.id.imgNext);
        final ImageView imgPrevious = findViewById(R.id.imgPrevious);
        final ImageView imgPause = findViewById(R.id.imgPause);
        final ImageView imgBack = findViewById(R.id.imgBack);


        // get intent from apdater
        Intent intentFromAdater = getIntent();
        // set value
        int mp3 = intentFromAdater.getIntExtra("mp3", 0);
        String name = intentFromAdater.getStringExtra("name");
        TextView tvName = findViewById(R.id.tvName);
        tvName.setText(name);
        int img = intentFromAdater.getIntExtra("img", 0);
        ImageView imgMusic = findViewById(R.id.img);
        imgMusic.setImageResource(img);

        // Khởi tạo ServiceConnection
        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {

                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBinder binder = (MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyService
                isBound = true;
            }
        };

        // Khởi tạo intent
        final Intent intent =
                new Intent(MainActivityPlay.this,
                        MyService.class);
        intent.putExtra("mp3", mp3);
        bindService(intent, connection,
                Context.BIND_AUTO_CREATE);

        imgPLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bắt đầu một service sủ dụng bind
                myService.play();
                imgPLay.setVisibility(View.INVISIBLE);
                imgPause.setVisibility(View.VISIBLE);
                System.out.println("play");
                isBound = true;
            }
        });

        imgPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Nếu Service đang hoạt động
                if(isBound){
                    myService.pause();
                    imgPLay.setVisibility(View.VISIBLE);
                    imgPause.setVisibility(View.INVISIBLE);
                    System.out.println("pause");
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound) {
                    // Tắt Service
                    unbindService(connection);
                    isBound = false;
                    finish();
                }
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // nếu service đang hoạt động
                if(isBound){
                    // tua bài hát
                    myService.fastForward();
                }else{
                    Toast.makeText(MainActivityPlay.this,
                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound){
                    // tua nguoc bài hát
                    myService.fastBack();
                }else{
                    Toast.makeText(MainActivityPlay.this,
                            "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
