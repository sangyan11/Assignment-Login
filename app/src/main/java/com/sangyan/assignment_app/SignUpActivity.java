package com.sangyan.assignment_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText name;
    EditText email_address;
    EditText password;
    EditText re_password;
 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
      name = findViewById(R.id.editTextTextPersonName);
      email_address = findViewById(R.id.editTextTextEmailAddress);
      password = findViewById(R.id.editTextTextPassword);
      re_password = findViewById(R.id.editTextTextPassword3);
      firebaseAuth = FirebaseAuth.getInstance();


    }

    public void signUp(View view) {
        registerUser();
    }

    private void registerUser() {
        String user_name = name.toString();
        String user_email = email_address.toString();
       String user_password  = password.toString();
       String re_enter_password = re_password.toString();
       if( user_email.isEmpty() || user_name.isEmpty() || user_password.isEmpty() || re_enter_password.isEmpty()){
           Toast.makeText(this,"This field cannot be empty", Toast.LENGTH_SHORT).show();
       }
       else{
           firebaseAuth.createUserWithEmailAndPassword(user_email, user_password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {



                           if (task.isSuccessful()) {
                               Toast.makeText(getApplicationContext(), "Account Created!!", Toast.LENGTH_SHORT).show();
                               FirebaseUser user = firebaseAuth.getCurrentUser();
                           }
                           else
                           {
                               Exception v = task.getException();
                               String error = v != null ? v.getMessage() : null;
                               Toast.makeText(SignUpActivity.this.getApplicationContext(), ("Error! " + error),  Toast.LENGTH_SHORT).show();
                           }

                           // ...
                       }
                   });
       }
    }
}