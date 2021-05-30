package com.himanshu.notemake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button createNote_btn_home = findViewById(R.id.createNote_btn_home);

        createNote_btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNoteCreateActivity();

            }
        });



    }

    private void openNoteCreateActivity() {
        Intent openNoteCreateActivity = new Intent(HomeActivity.this, CreateNewNoteActivity.class);
        startActivity(openNoteCreateActivity);
    }
}