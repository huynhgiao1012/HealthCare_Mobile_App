package com.example.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout IDNumInput, nameInput, PWInput, retypePWInput;
    private Button signUpBtn, toSignInPgBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        IDNumInput = findViewById(R.id.IDNumField);
        nameInput = findViewById(R.id.nameField);
        PWInput = findViewById(R.id.passwordField);
        retypePWInput = findViewById(R.id.retypePWField);

        signUpBtn = findViewById(R.id.signUpButton);
        toSignInPgBtn = findViewById(R.id.alreadyHadAccntBtn);

        signUpBtn.setOnClickListener(signUpHandler);

        toSignInPgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent(SignInActivity.class);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            toIntent(SignInActivity.class);
        }
    }

    private void toIntent(Class destinationClass) {
        Intent intent = new Intent(getApplicationContext(), destinationClass);
        startActivity(intent);
    }

    private View.OnClickListener signUpHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createUser();
        }
    };

    private void createUser() {
        String IDNum = IDNumInput.getEditText().getText().toString();
        String name = nameInput.getEditText().getText().toString();
        String password = PWInput.getEditText().getText().toString();
        String retypedPW = retypePWInput.getEditText().getText().toString();

        String hashedPW;

        if (IDNum.isEmpty()) {
            IDNumInput.setError(Utilities.setEmptyErrorText("ID number"));
            IDNumInput.requestFocus();
        }
        else if (name.isEmpty()) {
            nameInput.setError(Utilities.setEmptyErrorText("Name"));
            nameInput.requestFocus();
        }
        else if (password.isEmpty()) {
            PWInput.setError(Utilities.setEmptyErrorText("Password"));
            PWInput.requestFocus();
        }

        if (password.equals(retypedPW) && !password.isEmpty() && !retypedPW.isEmpty()) {
            signUpBtn.setEnabled(false);

            try {
                hashedPW = Utilities.hashPassword(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            mAuth.createUserWithEmailAndPassword(IDNum + "@healthcareapp.com", password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // TODO: Add logics to add user to db
                        FirebaseUser user = mAuth.getCurrentUser();
                        String UID = user.getUid();

                        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UID);

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("UID", UID);
                        hashMap.put("name", name);

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    toIntent(SignInActivity.class);
                                }
                            }
                        });
                    }
                    else {
                        Log.d("SignUpActivity", task.getException().getMessage());
                    }
                }
            });
        }
        else {
            retypePWInput.setError("Retyped password must match password");
            retypePWInput.requestFocus();
        }
    }
}