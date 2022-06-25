package com.example.lakuchadishes.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import com.example.lakuchadishes.databinding.ActivityDetailsBinding;
import com.example.lakuchadishes.ui.adapters.OfferAdapter;

import com.example.lakuchadishes.ui.models.OfferModel;
import com.example.lakuchadishes.ui.models.ProductModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    List<ProductModel> productModelList;
    List<OfferModel> offerModelList;
    //ProductAdapter productAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Product Details");

        db=FirebaseFirestore.getInstance();
        productModelList=new ArrayList<>();
        //productAdapter=new ProductAdapter(getApplicationContext(), productModelList);
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



        binding.btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        offers();

    }

    private void offers() {
        offerModelList=new ArrayList<>();
        db.collection("offers").addSnapshotListener((value, error) -> {
            if (error!=null){
                Log.d("Firestore error", error.getMessage());

            }
            else{
                assert value != null;
                for (DocumentChange doc : value.getDocumentChanges()){
                    if (doc.getType().equals(DocumentChange.Type.ADDED)){
                        OfferModel offerModel=doc.getDocument().toObject(OfferModel.class);
                        offerModelList.add(offerModel);
                    }

                    LinearLayoutManager offerLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerViewProducts.setLayoutManager(offerLayoutManager);
                    binding.recyclerViewProducts.setAdapter(new OfferAdapter(getApplicationContext(), offerModelList));
                    binding.recyclerViewProducts.setHasFixedSize(true);

                }
            }
        });
    }



}