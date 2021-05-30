package com.himanshu.notemake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_user extends AppCompatActivity {
    EditText emailET, passET, pass_again_et;
    EditText nameET;
    private String email, password,passwordAgain;
    String  name;
    com.himanshu.notemake.ValidInput validInput;
    private Button signup_btn;
    private ProgressBar progressBar4;

    // firebase auth obeject it is entry point
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);
        //start of signup
        validInput = new com.himanshu.notemake.ValidInput(this);
        emailET = findViewById(R.id.email_et);
        passET = findViewById(R.id.pass_et);
        pass_again_et = findViewById(R.id.pass_again_et);
        signup_btn = findViewById(R.id.signup_btn);
        nameET = findViewById(R.id.name_ET);
        // end of signup
        progressBar4 = findViewById(R.id.progressBar4);

        signup_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleSignUpBtnClick();
            }
        });

        // instance for fire base auth mAuth like object
        mAuth = FirebaseAuth.getInstance();
    }

    private void handleSignUpBtnClick()
    {    //ToDo hideProgressBar();
        //fetching string values
        email = emailET.getText().toString();
        password = passET.getText().toString();
        passwordAgain = pass_again_et.getText().toString();
        name = nameET.getText().toString();

        if(!name.isEmpty()) // if name is not empty
        {
            if(validInput.CheckEmailIsValid(email) && validInput.CheckPasswordIsValid(password)) // formate is valid or not
            {
                if (password.equals(passwordAgain)) // checking password and password again
                {
                    //if both password equal sign up the  user
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            //when the process is complete this is triggered when user is successfully created in firebase auth
                            if(task.isSuccessful())
                            {  //ToDo showProgressBar();
                                //firebase user object is holds user profile information  email passowrd and other
                                FirebaseUser user = mAuth.getCurrentUser(); //this method return current user , who logged in


                                Toast.makeText(Signup_user.this, "sign up successful for user : "+user.getEmail(), Toast.LENGTH_SHORT).show();

                                //save name in realtime database
                                saveNameInRealTimeDataBase(user);  // enter user value beacuse we mneed uuid

                            }
                            else
                            {  //ToDo hideProgressBar();
                                Toast.makeText(Signup_user.this, "Some error occured : "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {  //ToDo  hideProgressBar();
                    Toast.makeText(this, "password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {     //ToDo hideProgressBar();
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show();
        }




    }

    private void saveNameInRealTimeDataBase(FirebaseUser user) {

        //realtime data


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference noteRef = database.getReference();
        DatabaseReference nameReference = noteRef.child("Users").child(user.getUid()).child("Name");   // uid is current user id
        nameReference.setValue(name);
    }



//    private void showProgressBar() {
//
//    }

//    private void hideProgressBar() {
//
//    }
}