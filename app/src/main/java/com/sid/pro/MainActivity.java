package com.sid.pro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button button, btnregister;
    EditText email, password,fname,lname,uname,cpassword,phone;
    TextView txt;
    FirebaseAuth mFirebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText6);
        password = findViewById(R.id.editText5);
        txt = findViewById(R.id.textView);

        fname=findViewById(R.id.editText);
        lname=findViewById(R.id.editText2);
        uname=findViewById(R.id.editText3);
        cpassword=findViewById(R.id.editText4);
        phone=findViewById(R.id.editText7);


        btnregister = findViewById(R.id.button3);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pwd = password.getText().toString();

                String cpwd = cpassword.getText().toString();
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                String un = uname.getText().toString();
                String ph = phone.getText().toString();

                if (fn.isEmpty()) {
                    fname.setError("Please enter First name");
                    fname.requestFocus();
                } if (ln.isEmpty()) {
                    lname.setError("Please enter Last name");
                    lname.requestFocus();
                }if (un.isEmpty()) {
                    uname.setError("Please enter user name");
                    uname.requestFocus();
                }if (cpwd.isEmpty()) {
                    cpassword.setError("Please enter password");
                    cpassword.requestFocus();
                } if (ph.isEmpty()) {
                    phone.setError("Please enter phone number");
                    phone.requestFocus();
                } if (mail.isEmpty()) {
                    email.setError("Please enter email id");
                    email.requestFocus();
                }  if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                }  if (mail.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } if (!(mail.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Unsuccessfull,Please try again", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Registration Successfull,Login In here", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();

                }


            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this, Main2Activity.class);
             startActivity(intent);
          }
        });

         }
}
   /*     button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sign();
            }
        });
    }

    public void sign(){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
*/