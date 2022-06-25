package com.example.uhaibora.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.uhaibora.databinding.ActivityCartListBinding;
import com.example.uhaibora.ui.adapters.CartListAdapter;
import com.example.uhaibora.ui.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {
    ActivityCartListBinding binding;
    List<ProductModel> productModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("My Cart");

        productModelList= new ArrayList<>();
        binding.cartListRecyclerView.setAdapter(new CartListAdapter(getApplicationContext(), productModelList));
    }
}