package com.example.lakuchadishes.ui.credentials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lakuchadishes.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}