package com.himanshu.notemake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity<ValidInput> extends AppCompatActivity
{
    EditText emailET, passET;
    private Button loginBtn;
    private TextView signupText;
    com.himanshu.notemake.ValidInput validInput;
    FirebaseAuth mAuth; // use for login btn
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start of login part
        validInput = new com.himanshu.notemake.ValidInput(this);
        emailET = findViewById(R.id.email_et);
        passET = findViewById(R.id.pass_et);
        loginBtn = findViewById(R.id.login_btn);
        signupText = findViewById(R.id.signup_email_tv);
        // instance for fire base auth mAuth like object for login
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar3);
        //end of login part

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                handleLoginBtnClick();
            }
        });
        signupText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                handleSignUpEmailTv();
            }
        });
        // END login part


    }




    private void handleLoginBtnClick()
    {             hideProgressBar();
        String email = emailET.getText().toString();
        String password = passET.getText().toString();
        //ToDo: valid email and password field in android
        if(validInput.CheckEmailIsValid(email) && validInput.CheckPasswordIsValid(password))
        {
            Toast.makeText(LoginActivity.this, "Valid Email", Toast.LENGTH_SHORT).show();
            //ToDo:if both password equal sign up the  user
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    //ToDo:when the process is complete this is triggered when user is successfully login
                    if(task.isSuccessful())
                    {
                        //ToDo:login user

                        showProgressBar();
                        Toast.makeText(LoginActivity.this, "Login successful for user : "+email, Toast.LENGTH_SHORT).show();
                        // Show another screen on user login
                        Intent UserAccountActivity = new Intent(LoginActivity.this, UserAccountActivity.class);
                        startActivity(UserAccountActivity);
                    }
                    else
                    {     hideProgressBar();
                        Toast.makeText(LoginActivity.this, "Some error occured : "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else
        {
            Toast.makeText(LoginActivity.this, "Error fetch", Toast.LENGTH_SHORT).show();

        }
        //valid email and password in firebase
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void handleSignUpEmailTv()
    {
        Toast.makeText(LoginActivity.this, "Signup here", Toast.LENGTH_SHORT).show();
        Intent SignUpActivity = new Intent(LoginActivity.this,Signup_user.class);
        startActivity(SignUpActivity);

    }




}