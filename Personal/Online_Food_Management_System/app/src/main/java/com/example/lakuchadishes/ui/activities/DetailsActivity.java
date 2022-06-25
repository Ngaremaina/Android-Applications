package com.example.lakuchadishes.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.lakuchadishes.databinding.ActivityDetailsBinding;
import com.example.lakuchadishes.ui.adapters.OffersAdapter;
import com.example.lakuchadishes.ui.adapters.ProductAdapter;
import com.example.lakuchadishes.ui.models.OffersModel;
import com.example.lakuchadishes.ui.models.ProductModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    FirebaseFirestore db;
    List<ProductModel> newProductList;
    List<OffersModel> offerProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Product Details");

        db=FirebaseFirestore.getInstance();

        Intent intent=getIntent();

        Glide.with(DetailsActivity.this).load(intent.getStringExtra("image")).into(binding.imageView4);
        binding.textProductName.setText(intent.getStringExtra("name"));
        binding.textPrice.setText(String.valueOf(intent.getIntExtra("price", 0)));
        binding.textDescription.setText(intent.getStringExtra("description"));
        binding.textDelivery.setText(intent.getStringExtra("delivery"));

        newProducts();
        offers();
    }

    private void newProducts() {
        newProductList=new ArrayList<>();
        db.collection("new").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error: ", error.getMessage());
                }
                assert value != null;
                for (DocumentChange doc: value.getDocumentChanges()){
                    if (doc.getType().equals(DocumentChange.Type.ADDED)){
                        ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                        newProductList.add(productModel);

                    }
                    LinearLayoutManager newLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.newRecyclerView.setHasFixedSize(true);
                    binding.newRecyclerView.setLayoutManager(newLayoutManager);
                    binding.newRecyclerView.setAdapter(new ProductAdapter(getApplicationContext(), newProductList));
                }
            }
        });
    }

    private void offers() {
        offerProductList=new ArrayList<>();
        db.collection("offers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());

                }
                assert value != null;
                for (DocumentChange doc : value.getDocumentChanges()){
                    if (doc.getType().equals(DocumentChange.Type.ADDED)){
                        OffersModel offersModel=doc.getDocument().toObject(OffersModel.class);
                        offerProductList.add(offersModel);
                    }
                    LinearLayoutManager offerLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.offersRecyclerView.setHasFixedSize(true);
                    binding.offersRecyclerView.setLayoutManager(offerLayoutManager);
                    binding.offersRecyclerView.setAdapter(new OffersAdapter(getApplicationContext(), offerProductList));
                }
            }
        });
    }
}