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
import com.google.firebase.auth.FirebaseAuth;

public class UpdatePassword extends AppCompatActivity {
    private EditText  passET, pass_again_et;
    private Button updatePasswordBtn;
    private TextView changeTV;
    private ProgressBar progressBar4;

    //fire base update password
    com.himanshu.notemake.ValidInput validInput;
    FirebaseAuth FirebaseAuth;
    com.google.firebase.auth.FirebaseUser FirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        passET = findViewById(R.id.pass_et);
        pass_again_et = findViewById(R.id.pass_again_et);
        updatePasswordBtn = findViewById(R.id.update_password_btn);
        validInput = new com.himanshu.notemake.ValidInput(this);

        updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePasswordInFirebase();
            }
        });
        // instance for fire base auth for password update
        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        FirebaseUser = FirebaseAuth.getCurrentUser();
        progressBar4 = findViewById(R.id.progressBar4);
        changeTV = findViewById(R.id.changeTV);

    }
    //update password method
    private void UpdatePasswordInFirebase() {
        String password = passET.getText().toString();
        String passwordAgain = pass_again_et.getText().toString();

        if(validInput.CheckPasswordIsValid(password)) // formate is valid or not
        {
            if (password.equals(passwordAgain)) // checking password and password again
            {
                //if both password equal update in  firebase
                FirebaseUser.updatePassword(passwordAgain).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {

                        if(task.isSuccessful()){
                            Toast.makeText(UpdatePassword.this, "Password Updates  to : "+passwordAgain, Toast.LENGTH_SHORT).show();
                            Intent GoToLogin = new Intent(UpdatePassword.this, LoginActivity.class);
                            startActivity(GoToLogin);
                        }


                        else
                        {
                            Toast.makeText(UpdatePassword.this, "Some Error Occur"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
            else
            {
                Toast.makeText(this, "password does not match", Toast.LENGTH_SHORT).show();
            }
        }
    }

}