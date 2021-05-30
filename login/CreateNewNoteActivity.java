package com.himanshu.notemake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateNewNoteActivity extends AppCompatActivity {
    EditText contentTitle_ET, noteContent_ET;
    Button createNoteBtn;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);
        contentTitle_ET = findViewById(R.id.contentTitle_ET);
        noteContent_ET = findViewById(R.id.noteContent_ET);
        createNoteBtn = findViewById(R.id.createNoteBtn);
        createNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putNoteInFirebase();
            }
        });
//        user = firebaseAuth.getCurrentUser();
    }

    private void putNoteInFirebase() {

        String noteeContente = noteContent_ET.getText().toString();
        String noteTitlee = contentTitle_ET.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference noteeRef = database.getReference("NoteContent");
        user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference noteReference = noteeRef.child("Users").child(user.getUid()).child(user.getDisplayName()).child("Note Content Title");   // uid is current user id
        noteReference.setValue(noteTitlee);
        DatabaseReference contentReference = noteeRef.child("Users").child(user.getUid()).child(user.getDisplayName()).child("Note Content Text");   // uid is current user id
        contentReference.setValue(noteeContente);

        //new method to put note  start




    }
}