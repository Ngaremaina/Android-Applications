package com.example.lakuchadishes.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lakuchadishes.databinding.ActivityCartBinding;
import com.example.lakuchadishes.ui.adapters.CartListAdapter;
import com.example.lakuchadishes.ui.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    List<ProductModel> productModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("My Cart");

        productModelList=new ArrayList<>();
        binding.cartListRecyclerView.setAdapter(new CartListAdapter(getApplicationContext(), productModelList));
    }
}