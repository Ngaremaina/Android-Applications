package com.example.lakuchadishes.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lakuchadishes.R;
import com.example.lakuchadishes.databinding.FragmentMenuBinding;
import com.example.lakuchadishes.ui.adapters.ProductAdapter;
import com.example.lakuchadishes.ui.models.ProductModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private FirebaseFirestore db;
    private List<ProductModel> startersList;
    private List<ProductModel> mainDishesList;
    private List<ProductModel> dessertsList;
    private List<ProductModel> drinksList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();

        starters();
        mainDishes();
        desserts();
        drinks();

    }

    private void starters() {
        startersList=new ArrayList<>();
        db.collection("starters").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error:", error.getMessage());

                }
                else{
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            startersList.add(productModel);
                        }
                        LinearLayoutManager startersLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.startersRecyclerView.setHasFixedSize(true);
                        binding.startersRecyclerView.setLayoutManager(startersLayoutManager);
                        binding.startersRecyclerView.setAdapter(new ProductAdapter(getContext(), startersList));
                    }
                }

            }
        });
    }

    private void mainDishes() {
        mainDishesList=new ArrayList<>();
        db.collection("Main Dishes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());
                }
                else {
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            mainDishesList.add(productModel);

                        }
                        LinearLayoutManager mainDishesLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.mainDishesRecyclerView.setHasFixedSize(true);
                        binding.mainDishesRecyclerView.setLayoutManager(mainDishesLayoutManager);
                        binding.mainDishesRecyclerView.setAdapter(new ProductAdapter(getContext(), mainDishesList));
                    }
                }

            }
        });
    }

    private void desserts() {
        dessertsList=new ArrayList<>();
        db.collection("desserts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());
                }
                else {
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            dessertsList.add(productModel);

                        }
                        LinearLayoutManager dessertLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.dessertsRecyclerView.setHasFixedSize(true);
                        binding.dessertsRecyclerView.setLayoutManager(dessertLayoutManager);
                        binding.dessertsRecyclerView.setAdapter(new ProductAdapter(getContext(), dessertsList));
                    }
                }

            }
        });
    }

    private void drinks() {
        drinksList=new ArrayList<>();
        db.collection("drinks").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());
                }
                else {
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            drinksList.add(productModel);

                        }
                        LinearLayoutManager drinksLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.drinksRecyclerView.setHasFixedSize(true);
                        binding.drinksRecyclerView.setLayoutManager(drinksLayoutManager);
                        binding.drinksRecyclerView.setAdapter(new ProductAdapter(getContext(), drinksList));
                    }
                }

            }
        });
    }
}