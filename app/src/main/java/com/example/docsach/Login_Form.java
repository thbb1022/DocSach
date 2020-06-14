package com.example.docsach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.docsach.R.id.txt_password;

public class Login_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);

        //Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_book_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(txt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Login_Form.this, "Password To Short", Toast.LENGTH_SHORT).show();

                }

//            firebaseAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                Toast.makeText(Login_Form.this, "1231123 Faile", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Toast.makeText(Login_Form.this, "Login Faile", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));


                                } else {
                                    Toast.makeText(Login_Form.this, "Login Faile", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });

    }

    public void btn_signupForm(View view){

        startActivity(new Intent(getApplicationContext(),Signup_Form.class));

    }
}
