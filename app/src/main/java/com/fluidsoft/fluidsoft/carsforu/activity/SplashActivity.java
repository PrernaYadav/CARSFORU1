package com.fluidsoft.fluidsoft.carsforu.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fluidsoft.fluidsoft.carsforu.R;

public class SplashActivity extends AppCompatActivity {
public final int SPLASH_TIME_OUT=3000;
//    public GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
               /* mp.stop();*/
                SplashActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
