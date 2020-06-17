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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail,txtUserName,txtPassword,txtConfirmPassword;
    RadioButton rdMale,rdFemale;
    Button btnRegister;
    private ProgressBar progressBar;
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
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtEmail.getText().toString().trim();
                final String username = txtUserName.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();
                //Check ky tu dac biet
                Pattern p = Pattern.compile("[^a-bA-B0-9]");
                if (TextUtils.isEmpty(username)) {
                   txtUserName.setError(getString(R.string.input_error_name));
                    txtUserName.requestFocus();
                    return;
                } else {if(p.matcher(username).find()){
                    txtUserName.setError(getString(R.string.input_error_name_key)); // check name ky tu dac biet
                    txtUserName.requestFocus();
                    return;
                }
                }

                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError(getString(R.string.input_error_email));
                    txtEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                   txtPassword.setError(getString(R.string.input_error_password));
                   txtPassword.requestFocus();
                    return;
                }else {if(p.matcher(password).find()){
                    txtPassword.setError(getString(R.string.input_error_password_key)); // check pass ky tu dac biet
                    txtPassword.requestFocus();
                    return;
                }
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    txtConfirmPassword.setError(getString(R.string.input_error_password));
                    txtConfirmPassword.requestFocus();
                    return;
                }
                if(password.length()<6){
                    txtPassword.setError(getString(R.string.input_error_password_length)); // check pass nho hon 6kitu
                    return;
                }

                if(password.equals(confirmpassword)){
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User user = new User(
                                                username,email
                                        );
                                        // them user vao db
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                progressBar.setVisibility(View.GONE);
                                                if (task.isSuccessful()) {
                                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                }else
                                                {

                                                    Toast.makeText(Signup_Form.this, "**Fail11**", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });

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
