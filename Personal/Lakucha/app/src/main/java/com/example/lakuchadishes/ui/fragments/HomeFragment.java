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

import com.example.lakuchadishes.databinding.FragmentHomeBinding;
import com.example.lakuchadishes.ui.adapters.ImageAdapter;
import com.example.lakuchadishes.ui.adapters.OfferAdapter;
import com.example.lakuchadishes.ui.adapters.ProductAdapter;
import com.example.lakuchadishes.ui.models.ImageModel;
import com.example.lakuchadishes.ui.models.OfferModel;
import com.example.lakuchadishes.ui.models.ProductModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    List<ImageModel> imageModelList;
    List<ProductModel> productModelList;
    List<ProductModel> newproductModelList;
    List<OfferModel> offerModelList;

    FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();

        sliderImages();
        popular();
        offers();
        newDishes();

    }



    private void sliderImages() {
        imageModelList=new ArrayList<>();
        imageModelList.add(new ImageModel(1,"","Delivery"));
        imageModelList.add(new ImageModel(2,"","Doctor"));
        imageModelList.add(new ImageModel(3,"","Assistant"));

        ImageAdapter imageAdapter=new ImageAdapter(getContext(), imageModelList);
        binding.viewPager.setAdapter(imageAdapter);

        Timer sliderImage=new Timer();
        sliderImage.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HomeFragment.this.requireActivity().runOnUiThread(() -> {
                    if (binding.viewPager.getCurrentItem()<imageModelList.size()-1){
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                    }
                    else{
                        binding.viewPager.setCurrentItem(0);
                    }
                });

            }
        }, 2200,6000);
    }

    private void popular() {
        productModelList=new ArrayList<>();
        db.collection("popular").addSnapshotListener((value, error) -> {
            if (error!=null){
                Log.d("Firestore error", error.getMessage());
            }
            else{
                assert value != null;
                for (DocumentChange doc : value.getDocumentChanges()){
                    if (doc.getType().equals(DocumentChange.Type.ADDED)){
                        ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                        productModelList.add(productModel);
                    }
                    LinearLayoutManager popularLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.popularRecyclerView.setLayoutManager(popularLayoutManager);
                    binding.popularRecyclerView.setAdapter(new ProductAdapter(getContext(), productModelList));
                    binding.popularRecyclerView.setHasFixedSize(true);

                }
            }
        });
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

                    LinearLayoutManager offerLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.offerRecyclerView.setLayoutManager(offerLayoutManager);
                    binding.offerRecyclerView.setAdapter(new OfferAdapter(getContext(), offerModelList));
                    binding.offerRecyclerView.setHasFixedSize(true);

                }
            }
        });
    }

    private void newDishes() {
        newproductModelList=new ArrayList<>();
        db.collection("new").addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.d("Firestore error", error.getMessage());
            }
            else {
                assert value != null;
                for (DocumentChange doc : value.getDocumentChanges()){
                    if (doc.getType().equals(DocumentChange.Type.ADDED)){
                        ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                        newproductModelList.add(productModel);
                    }
                    LinearLayoutManager newLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.newProductRecyclerView.setLayoutManager(newLayoutManager);
                    binding.newProductRecyclerView.setAdapter(new ProductAdapter(getContext(), newproductModelList));
                    binding.newProductRecyclerView.setHasFixedSize(true);
                }
            }

        });
    }
}