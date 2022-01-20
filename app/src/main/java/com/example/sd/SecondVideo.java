package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class SecondVideo extends AppCompatActivity {

    VideoView video;
    ProgressBar progressBar;
    String videoUrl = "https://firebasestorage.googleapis.com/v0/b/sd-project-c0f91.appspot.com/o/y2meta.com-How%20the%20COVID-19%20virus%20is%20transmitted-(1080p).mp4?alt=media&token=81b970ab-9db4-4ba7-922b-79a26d27810a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_video);
        progressBar = findViewById(R.id.progressBar7);

        video = findViewById(R.id.videoView);
        Uri uri = Uri.parse(videoUrl);
        video.setVideoURI(uri);
        video.start();

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}