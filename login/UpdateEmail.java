package com.himanshu.notemake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UpdateEmail extends AppCompatActivity
{

    private EditText oldEmailET, newEmailET;
    private Button updateEmailBtn;
    //fire base update password
    com.himanshu.notemake.ValidInput validInput;
    private ProgressBar progressBar;
    // firebase
    com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    com.google.firebase.auth.FirebaseUser FirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        progressBar = findViewById(R.id.progressBar3) ;
        oldEmailET = findViewById(R.id.oldEmail_ET);
        newEmailET = findViewById(R.id.newEmail_ET);
        updateEmailBtn = findViewById(R.id.update_email_btn);
        updateEmailBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateEmailInFirebase();
            }
        });

        validInput = new com.himanshu.notemake.ValidInput(this);
        // instance for fire base auth for password update
        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        FirebaseUser = FirebaseAuth.getCurrentUser();
    }

    private void updateEmailInFirebase()
    {
        //update email in firebase
        String oldEmail = oldEmailET.getText().toString();
        String newEmail = newEmailET.getText().toString();
        if(validInput.CheckEmailIsValid(newEmail))
        {
            if(oldEmail.equals(newEmail))
            {
                Toast.makeText(this, "New Email Can't be same as old one", Toast.LENGTH_SHORT).show();
            }
            else
            {
                FirebaseUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(UpdateEmail.this, "New Email Updated to"+newEmail, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(UpdateEmail.this, "Some error occur"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}