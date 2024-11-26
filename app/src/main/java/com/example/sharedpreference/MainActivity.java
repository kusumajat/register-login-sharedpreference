package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sharedpreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Ensure the correct view is set

        prefManager = PrefManager.getInstance(this);
        checkLoginStatus();

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setLoggedIn(false);  // Set login status to false
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);  // Start LoginActivity
                finish();  // Finish current activity
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.clear();  // Clear preferences
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);  // Start RegisterActivity
                finish();  // Finish current activity
            }
        });
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = prefManager.isLoggedIn();  // Check login status
        if (!isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);  // Start LoginActivity if not logged in
            finish();  // Finish current activity
        }
    }
}
