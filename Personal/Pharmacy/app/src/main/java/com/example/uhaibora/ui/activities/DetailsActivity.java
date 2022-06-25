package com.example.uhaibora.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.uhaibora.databinding.ActivityDetailsBinding;
import com.example.uhaibora.ui.adapters.ProductAdapter;
import com.example.uhaibora.ui.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    List<ProductModel> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Product Details");

        //capture incoming intent

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price",0);
        String description = intent.getStringExtra("description");
        int rating = intent.getIntExtra("rating",0);

        //load into our Details activity view layout

        Glide.with(DetailsActivity.this).load(image).into(binding.imageViewProduct);
        binding.textViewProductName.setText(name);
        binding.textPrice.setText(String.valueOf(price));
        binding.textViewDescription.setText(description);
        binding.textViewRating.setText(String.valueOf(rating));

        products();
        
        binding.btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });

    }

    private void addtoCart() {
    }

    private void products () {
        productList=new ArrayList<>();
        final String BASE_URL = "http://192.168.100.53/pharmacy/popular.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String image = object.getString("image");
                                String name = object.getString("name");
                                int price = object.getInt("price");
                                ProductModel productModel=new ProductModel(image,name, price);
                                productList.add(productModel);
                            }

                        }catch (Exception e){
                            Log.d("Database error", e.getMessage());

                        }
                        LinearLayoutManager productsLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.recyclerViewProducts.setLayoutManager(productsLayoutManager);
                        binding.recyclerViewProducts.setAdapter(new ProductAdapter(getApplicationContext(), productList));
                        binding.recyclerViewProducts.setHasFixedSize(true);

                        /*productAdapter = new ProductAdapter(getContext(), productList);
                        binding.popularRecyclerView.setAdapter(productAdapter);*/


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}