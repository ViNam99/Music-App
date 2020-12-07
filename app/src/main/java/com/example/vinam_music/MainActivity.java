package com.example.vinam_music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.vinam_music.adapter.MusicAdapter;
import com.example.vinam_music.entities.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Music> listMusic;
    private MusicAdapter musicAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listMusic = new ArrayList<>();
        listMusic.add(new Music("Buồn vương màu áo", R.raw.buonvuongmauao_nguyenhung, R.drawable.image5));
        listMusic.add(new Music("Cứ yêu đi", R.raw.cuyeudi, R.drawable.image1));
        listMusic.add(new Music("Em Bang qua", R.raw.embangqua, R.drawable.image2));
        listMusic.add(new Music("Một nửa hạnh phúc", R.raw.motnuahanhphuc, R.drawable.image3));
        listMusic.add(new Music("Trên tình bạn dưới tình yêu", R.raw.trentinhbanduoitinhyeu, R.drawable.image4));
        listMusic.add(new Music("Yêu từ cái buồn đầu tiên", R.raw.yeutucaibuondautien, R.drawable.image1));
        listMusic.add(new Music("Em Bang qua", R.raw.embangqua, R.drawable.image6));
        listMusic.add(new Music("Cứ yêu đi", R.raw.cuyeudi, R.drawable.image5));
        listMusic.add(new Music("Một nửa hạnh phúc", R.raw.motnuahanhphuc, R.drawable.image2));

        recyclerView = findViewById(R.id.rcv);

        musicAdapter = new MusicAdapter(this, listMusic);

        recyclerView.setAdapter(musicAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
    }
}