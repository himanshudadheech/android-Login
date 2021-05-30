package com.himanshu.notemake;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.regex.Pattern;
// this class is for login
public class ValidInput {
    Context context;

    ValidInput(Context context) {
        this.context=context;
    }

    // valid email


    //email end
    //passwd start




    public boolean CheckEmailIsValid(String email) {
        if(email.length() == 0)
        {
            Toast.makeText(context, "Please Enter your Email ID", Toast.LENGTH_SHORT).show();
            return false;
        }


        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(context, "Please Enter Correct email", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }



    public boolean CheckPasswordIsValid(String password) {
        if(password.length()==0)
        {
            Toast.makeText(context, "Please Enter your password ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length()<6)
        {
            Toast.makeText(context, "Please Enter Password at least of 8 character", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            return true;
        }
    }
}
