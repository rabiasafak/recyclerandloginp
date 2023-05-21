package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button loginBtn;
    ProgressBar progresbar;
    TextView createAccountBtnTextView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginBtn= findViewById(R.id.giriş_button);
        createAccountBtnTextView = findViewById(R.id.crate_account_textview_btn);
        progresbar = findViewById(R.id.progress_bar);


        loginBtn.setOnClickListener((v)-> loginUser());
        createAccountBtnTextView.setOnClickListener((v)->startActivity(new Intent(LoginActivity.this,CreateAccountActivity.class)));
    }
    void loginUser(){
        String email= emailEditText.getText().toString();
        String password= passwordEditText.getText().toString();

        boolean isValiadet=validateData(email,password);
        if(!isValiadet){
            return;
        }
  loginAccountInFirebase(email,password);
    }

    void loginAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if ((task.isSuccessful())){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }else{
                        Utility.showToast(LoginActivity.this,"Email Onaylanmadı");
                    }
                }else{
                    Utility.showToast(LoginActivity.this,task.getException().getLocalizedMessage());
                }
            }
        });

    }
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progresbar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else{
            progresbar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("email doğru değil");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Parola Yeterli Uzunlukta Olmalı");
            return false;
        }

        return true;
    }
}