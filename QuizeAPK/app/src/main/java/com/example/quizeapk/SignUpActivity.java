package com.example.quizeapk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private EditText editTextEmail, editTextCity, editTextPhone;
    private Button buttonSignUp, buttonBack;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        initializeViews();
        buttonSignUp.setOnClickListener(v -> signUpUser());
        buttonBack.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        editTextUsername = findViewById(R.id.editTextSignUpUsername);
        editTextPassword = findViewById(R.id.editTextSignUpPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCity = findViewById(R.id.editTextCity);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSignUp = findViewById(R.id.buttonSignUpSubmit);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void signUpUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (!validateInputs(username, password, confirmPassword, email, city, phone)) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        saveUserData(userId, username, email, city, phone);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration failed",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateInputs(String username, String password,
                                   String confirmPassword, String email,
                                   String city, String phone) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                email.isEmpty() || city.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password should be at least 6 characters",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveUserData(String userId, String username,
                              String email, String city, String phone) {
        User user = new User(username, email, city, phone);
        mDatabase.child("users").child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this,
                                "Registration successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this,
                                "Failed to save user data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}