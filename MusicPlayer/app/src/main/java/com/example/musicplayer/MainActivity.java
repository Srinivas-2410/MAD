package com.example.musicplayer;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button btnPlay,btnPause,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnPlay=findViewById(R.id.play);
        btnPause=findViewById(R.id.pause);
        btnStop=findViewById(R.id.stop);
        if(mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this,R.raw.music);
        }
        //play button
        btnPlay.setOnClickListener(V->{
            if(!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });
        //pause button
        btnPause.setOnClickListener(V->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });
        //stop button
        btnStop.setOnClickListener(V-> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer=MediaPlayer.create(this,R.raw.music);
            }
        });
    }
}