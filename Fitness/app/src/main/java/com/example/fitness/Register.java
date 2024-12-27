package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button registerButton;
    private TextView textViewSignIn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth and Database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        registerButton = findViewById(R.id.registerButton);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        progressBar = findViewById(R.id.progressBar);

        registerButton.setOnClickListener(view -> validateAndRegister());

        textViewSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
            finish();
        });
    }

    private void validateAndRegister() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Reset any previous errors
        editTextName.setError(null);
        editTextEmail.setError(null);
        editTextPassword.setError(null);
        editTextConfirmPassword.setError(null);

        // Validate inputs
        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        // Show progress and disable button
        progressBar.setVisibility(View.VISIBLE);
        registerButton.setEnabled(false);

        registerUser(name, email, password);
    }

    private void registerUser(String name, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    // Hide the progress bar and enable the register button
                    progressBar.setVisibility(View.INVISIBLE);
                    registerButton.setEnabled(true);

                    if (task.isSuccessful()) {
                        // Registration was successful
                        String userId = firebaseAuth.getCurrentUser().getUid();
                        User user = new User(userId, name, email);

                        // Save user data to the database
                        databaseReference.child(userId).setValue(user)
                                .addOnSuccessListener(aVoid -> {
                                    // Show success message
                                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                                    // Navigate to the login screen
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);
                                    finish();  // Make sure to call finish() so that the Register activity is removed from the stack
                                })
                                .addOnFailureListener(e -> {
                                    // Show error message in case of failure to save user data
                                    Toast.makeText(Register.this, "Failed to save user data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                });
                    } else {
                        // If registration failed, handle the error
                        handleRegistrationError(task.getException());
                    }
                });
    }


    private void handleRegistrationError(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            editTextPassword.setError("Password is too weak");
            editTextPassword.requestFocus();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            editTextEmail.setError("Invalid email format");
            editTextEmail.requestFocus();
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            editTextEmail.setError("Email already registered");
            editTextEmail.requestFocus();
        } else {
            Toast.makeText(Register.this, "Registration failed: " + exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
