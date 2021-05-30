package com.himanshu.notemake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//ToDo : this Activity is when user login successfully
public class UserAccountActivity extends AppCompatActivity {

    private TextView welcomeTv;

    private Button logoutBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser user ;

    //for update password button
    private Button updatePassword;
    //for update email button
    private Button updateEmail;

    Context context; // use for alertdialog.builder

    private Button createNote_btn;
    private Button homeActivityGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        logoutBtn = findViewById(R.id.logoutBtn);
        welcomeTv = findViewById(R.id.welcomeTv);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        welcomeTv.setText("hi "+user.getEmail()+" !!");
        context = this;// use for alertdialog.builde

        //name
        homeActivityGo = findViewById(R.id.homeActivityGo);

        homeActivityGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openHomeCreateActivity = new Intent(UserAccountActivity.this, HomeActivity.class);
                startActivity(openHomeCreateActivity);
            }
        });

        //name

        //for update password
        updatePassword = findViewById(R.id.updatePassword);
        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowUpdatePassword();
            }
        });
        // updatePassword end

        //update email start
        updateEmail = findViewById(R.id.updateEmail);
        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEmailInFirebase();
            }
        });
        // update email end


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogoutBtn();

            }
        });


        createNote_btn = findViewById(R.id.createNote_btn);

        createNote_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNoteCreateActivity();

            }
        });
    }

    //open create new note
    private void openNoteCreateActivity() {
        Intent openNoteCreateActivity = new Intent(UserAccountActivity.this, CreateNewNoteActivity.class);
        startActivity(openNoteCreateActivity);
    }
    //open create new note


    // update email method start
    private void UpdateEmailInFirebase() {
        //ToDo: update email
        Intent UpdateEmailActivity = new Intent(UserAccountActivity.this, UpdateEmail.class);
        startActivity(UpdateEmailActivity);
    }
    //update email end


    // logout task handel
    private void handleLogoutBtn() {
        //Alert box Start
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are You Sure, Want to Logout").setPositiveButton("Yes, Logout" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // logout here
                firebaseAuth.signOut(); // end the session

                finish();// end the current user activity
            }

        }).setNegativeButton("No!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss(); // dismiss dialog if user say no
            }
        });
        AlertDialog alertDialog = builder.create() ;
        alertDialog.show();
        // Alert box End


    }
    // logout button task finsh

    // update password button start
    private void ShowUpdatePassword() {
        Toast.makeText(context, "Update Password Here", Toast.LENGTH_SHORT).show();
        //ToDo: create new activity updatepassword
        Intent UpdatePasswordActivity = new Intent(UserAccountActivity.this, UpdatePassword.class);
        startActivity(UpdatePasswordActivity);
    }
    // update passowrd button finish
}