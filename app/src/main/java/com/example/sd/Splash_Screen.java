package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topanim,btanim;
    TextView logo,logo2;
    ImageView imj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen);

        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        btanim =  AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //logo = findViewById(R.id.logotxt);
        //logo2 = findViewById(R.id.sublog);
        imj = findViewById(R.id.logo_img);

        //logo.setAnimation(btanim);
        //logo2.setAnimation(btanim);
        imj.setAnimation(btanim);

         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this,Showblack.class);
                //startActivity(intent);
                //finish();
                Pair[]pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(imj,"logo_imgg");
                //pairs[0] = new Pair<View,String>(logo,"logo_txtt");
               // pairs[1] = new Pair<View,String>(logo2,"logo_txtt");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splash_Screen.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        },SPLASH_SCREEN);

    }
}