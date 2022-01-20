package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class Showblack extends AppCompatActivity {

    private ViewPager slidepage;
    Button btnn;
    private LinearLayout linlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showblack);

       /* final LoadingDialogue loadingDialogue = new LoadingDialogue(Showblack.this);
        loadingDialogue.startLoadingActivity();*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loadingDialogue.dismissDialog();
                Intent intent = new Intent(Showblack.this,Startup.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}