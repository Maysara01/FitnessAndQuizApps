package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Setting extends AppCompatActivity {

    private Button logoutButton;
    private FirebaseAuth firebaseAuth;

    // TextView to display user email
    private TextView tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        // Find the TextView and logout button
        tvUserEmail = findViewById(R.id.tvUserEmail);
        logoutButton = findViewById(R.id.logoutButton);

        // Get the current logged-in user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            // Display the user's email if user is logged in
            String userEmail = user.getEmail();
            tvUserEmail.setText("Email: " + userEmail);
        } else {
            // If the user is not logged in, show a message
            tvUserEmail.setText("Email: Not logged in");
        }

        // Set up logout button
        logoutButton.setOnClickListener(v -> {
            // Log out the user
            firebaseAuth.signOut();

            // Redirect to the Login activity
            Intent intent = new Intent(Setting.this, Login.class);
            startActivity(intent);
            finish(); // Close this activity
        });
    }
}
