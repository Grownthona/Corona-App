package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class Mask_Video extends AppCompatActivity {

    VideoView video;
    ProgressBar progressBar;
    String videoUrl = "https://firebasestorage.googleapis.com/v0/b/sd-project-c0f91.appspot.com/o/y2meta.com-Find%20a%20mask%20that%20fits%20your%20face%20the%20best-(1080p).mp4?alt=media&token=bf0b8a2d-c2b6-4956-ba47-564bb0eb02f1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask__video);
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