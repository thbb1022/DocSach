package com.example.docsach;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail,txtUserName,txtPassword,txtConfirmPassword;
    RadioButton rdMale,rdFemale;
    Button btnRegister;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        //Logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_menu_book_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //

        // getSupportActionBar().setTitle("Signup");

        txtEmail = (EditText)findViewById(R.id.txt_Email);
        txtUserName = (EditText) findViewById(R.id.txt_UserName);
        txtPassword = (EditText)findViewById(R.id.txt_Password);
        txtConfirmPassword = (EditText)findViewById(R.id.txt_ConfirmPassword);
        rdMale = (RadioButton)findViewById(R.id.rd_Male);
        rdFemale = (RadioButton)findViewById(R.id.rd_Femal);
        btnRegister = (Button) findViewById(R.id.btn_Register);


        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String username = txtUserName.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Signup_Form.this, "Please Enter UserName", Toast. LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(Signup_Form.this, "Please Enter ConfirmPassword", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup_Form.this, "Password To Short", Toast.LENGTH_SHORT).show();

                }

                if(password.equals(confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    } else {
                                        Toast.makeText(Signup_Form.this, "**Fail**", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
                else {

                    Toast.makeText(Signup_Form.this, "**Password are not matching**", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

}
