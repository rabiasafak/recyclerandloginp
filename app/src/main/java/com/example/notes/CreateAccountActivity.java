package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class CreateAccountActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText,confirmEditText;
    Button createAccountButton;
    ProgressBar progresbar;
    TextView loginbtnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        emailEditText = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        confirmEditText = findViewById(R.id.confirm_edittext);
        createAccountButton = findViewById(R.id.kayitol_button);
        progresbar = findViewById(R.id.progress_bar);
        loginbtnTextView = findViewById(R.id.login_textviewbutton);

        createAccountButton.setOnClickListener(v ->createAccount());
        loginbtnTextView.setOnClickListener(v->finish());

    }
        void createAccount(){
        String email= emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String confirmPassword= confirmEditText.getText().toString();

        boolean isValidated=validateData(email,password,confirmPassword);
        if(!isValidated){
            return;
        }
        createAccountInFirebase(email,password);
    }

    void createAccountInFirebase(String email,String password){
        changeInProgress(true);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateAccountActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        changeInProgress(false);

                        if(task.isSuccessful()){
                            Utility.showToast(CreateAccountActivity.this, "Hesap Oluşturuldu,Doğrulamak İçin Mail Kutunuzu Kontrol Edin");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();;
                        }else {
                            Utility.showToast(CreateAccountActivity.this, task.getException().getLocalizedMessage());
                        }
                    }
                }
                );

    }
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progresbar.setVisibility(View.VISIBLE);
            createAccountButton.setVisibility(View.GONE);
        }else{
            progresbar.setVisibility(View.GONE);
            createAccountButton.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password,String confirmPassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("email doğru değil");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Parola Yeterli Uzunlukta Olmalı");
            return false;
        }
        if(!password.equals(confirmPassword)){
            confirmEditText.setError("Parolalar Eşleşmiyor");
            return false;
        }
        return true;
    }
}