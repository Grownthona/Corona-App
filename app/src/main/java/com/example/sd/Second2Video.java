package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class Second2Video extends AppCompatActivity {

    VideoView video;
    ProgressBar progressBar;
    String videoUrl = "https://firebasestorage.googleapis.com/v0/b/sd-project-c0f91.appspot.com/o/y2meta.com-COVID-19%20and%20the%20cold%20and%20flu%20season-(1080p).mp4?alt=media&token=e5061285-ec8f-47d5-b8e3-28adfc441898";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2_video);

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