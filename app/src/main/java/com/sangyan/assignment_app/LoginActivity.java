package com.sangyan.assignment_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
 EditText email;
 EditText password;
 Button login;
 Button signup;
 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email  = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        Login();
    }

    private void Login() {
     String user_email = email.toString();
     String user_password = password.toString();
     if(user_email.isEmpty() || user_password.isEmpty()){
         Toast.makeText(this,"This field cannot be empty",Toast.LENGTH_SHORT).show();
     }
     else{
         firebaseAuth.signInWithEmailAndPassword(user_email,user_password)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){

                     @Override
                     public void onComplete(@NonNull  Task<AuthResult> task) {
//
                         if (task.isSuccessful()) {
                             FirebaseUser user = firebaseAuth.getCurrentUser();
                             startActivity( new Intent(getApplicationContext(), MainActivity.class));

                         }
                         else
                         {
                             Exception var4 = task.getException();
                             String error = var4 != null ? var4.getMessage() : null;
                             Toast.makeText(LoginActivity.this.getApplicationContext(), ("Error! " + error),  Toast.LENGTH_SHORT).show();
                         }
                     }
                 });

     }


    }

    public void signup(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}