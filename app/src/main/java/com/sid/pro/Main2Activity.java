package com.sid.pro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mFirebaseAuth;
    private Button btnlogin;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText9);
        password = findViewById(R.id.editText8);
        btnlogin = findViewById(R.id.button2);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
            if( mFirebaseUser!=null){
                Toast.makeText(Main2Activity.this,"You are Sucessfully Logged In",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this, Logout.class);
                startActivity(intent);

            }
            else {
                Toast.makeText(Main2Activity.this,"Please login in",Toast.LENGTH_SHORT).show();

            }

            }
        };

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pwd = password.getText().toString();
                if (mail.isEmpty()) {
                    email.setError("Please enter email id");
                    email.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                } else if (mail.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(mail.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(mail,pwd).addOnCompleteListener(Main2Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Main2Activity.this, "Login Error,Please try again", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Main2Activity.this, "Logged In Sucessfully", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(Main2Activity.this, Logout.class);
                                 startActivity(intent);
                            }
                        }
                    });


                    }
                    else {
                    Toast.makeText(Main2Activity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
   /* @Override
    protected void onStart(){
    super.onStart();
    mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }*/
}
