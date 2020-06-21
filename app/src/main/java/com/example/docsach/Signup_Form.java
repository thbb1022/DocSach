package com.example.docsach;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail,txtUserName,txtPassword,txtConfirmPassword;
    RadioButton rdMale,rdFemale;
    Button btnRegister;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    public static final String TAG = Signup_Form.class.getSimpleName(); //debug log
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
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pp = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Pattern p = Pattern.compile("[^a-zA-Z0-9]");    //Check ky tu dac biet
                if (TextUtils.isEmpty(username)) {
                   txtUserName.setError(getString(R.string.input_error_name));
                    txtUserName.requestFocus();
                    return;
                }
                if(p.matcher(username).find()){
                    txtUserName.setError(getString(R.string.input_error_name_key)); // check username ko co ky tu dac biet
                    txtUserName.requestFocus();
                    return;
                }
                if(!pp.matcher(email).find()){
                    txtEmail.setError(getString(R.string.input_error_email_invalid)); // kiem tra phai dung la mail
                    txtEmail.requestFocus();
                    return;
                }


                if (TextUtils.isEmpty(email)) {
                    txtEmail.setError(getString(R.string.input_error_email));  //check mail rong
                    txtEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                   txtPassword.setError(getString(R.string.input_error_password));
                   txtPassword.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {
                    txtConfirmPassword.setError(getString(R.string.input_error_password));
                    txtConfirmPassword.requestFocus();
                    return;
                }
                if(password.length()<6){
                    txtPassword.setError(getString(R.string.input_error_password_length)); // check pass nho hon 6kitu
                    return;
                }//check email already exist or not.
                firebaseAuth.fetchSignInMethodsForEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                                if (!isNewUser) {
                                    Log.e("TAG", "Is Old User!");
                                    txtEmail.setError(getString(R.string.input_error_email_has_already));
                                    txtPassword.requestFocus();
                                    return;
                                }

                            }
                        });

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

                                                    Toast.makeText(Signup_Form.this, "**lOGIN FAIL**", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });

                                    } else {
                                        progressBar.setVisibility(View.INVISIBLE);
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
