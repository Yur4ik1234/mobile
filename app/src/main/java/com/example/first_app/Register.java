package com.example.first_app;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText Name,Email,Password,ConfPass,Phone;
    Button Register;
    TextView Loginpath;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name =  findViewById(R.id.editTextTextPersonName3);
        Email = findViewById(R.id.editTextTextPersonName2);
        Password = findViewById(R.id.editTextTextPersonName4);
        ConfPass = findViewById(R.id.editTextTextPersonName5);
        Phone = findViewById(R.id.editTextTextPersonName);
        Loginpath = findViewById(R.id.textView11);
        Register = findViewById(R.id.button8);
        progressBar = findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("users");

      /*if  (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), FeelActivity.class));
            finish();
        }*/

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confpass = ConfPass.getText().toString().trim();
                String name = Name.getText().toString().trim();
                String phone = Phone.getText().toString().trim();

                Pattern pattern_phone = Pattern.compile("^((\\+?380)([0-9]{9}))$");
                Matcher matcher = pattern_phone.matcher(phone);


                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is required");
                    return;
                }
                if (password.length()< 6)
                {
                    Password.setError(" Password must be more than 6 characters");
                    return;
                }

                if (TextUtils.isEmpty(phone))
                {
                    Phone.setError("Phone is required");
                    return;
                }
                else if (!matcher.matches())
                {
                    Phone.setError("Incorrect phone number");
                    return;
                }


                 if (!password.equals(confpass))
                {
                    ConfPass.setError("Password should be as an last line");
                    return;
                }
                Users user= new Users (name,email,password);
                myref.child(phone).setValue(user);
                progressBar.setVisibility(View.VISIBLE);
                // Register the user

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), FeelActivity.class));

                        }

                        else
                        {
                            Toast.makeText(Register.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
                }
            });

        Loginpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


    }
}