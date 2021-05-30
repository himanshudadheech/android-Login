package com.himanshu.notemake;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser Currentuser;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // AUTH object

        // current user Object = it is Currently logged in use . If not logged in this is null


        firebaseAuth = FirebaseAuth.getInstance();
        Currentuser = firebaseAuth.getCurrentUser();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Your Code
                if(Currentuser != null){
                    //if user already login
                    Intent StartAct = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(StartAct);
                }
                else {
                    // if not login we tell him log in
                    Intent StartAloh = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(StartAloh);
                }
            }
        }, 3000);

    }
}